package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        MealsUtil.meals.stream().
                map(meal -> new Meal(meal.getDateTime(), meal.getDescription() + " for Admin", meal.getCalories())).
                forEach(meal -> save(meal, 2));
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getFilteredByPredicate(userId, meal -> true);
    }

    @Override
    public List<Meal> getAllBetweenDates(int userId, LocalDate startDate, LocalDate endDate) {
        return getFilteredByPredicate(userId, meal -> DateTimeUtil.isBetweenTwoDates(meal.getDate(), startDate, endDate));
    }

    private List<Meal> getFilteredByPredicate(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        if (userMeals == null) {
            return Collections.emptyList();
        }
        return userMeals.values().stream().
                filter(filter).
                sorted(Comparator.comparing(Meal::getDateTime).reversed()).
                collect(Collectors.toList());
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals == null ? null : userMeals.get(id);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals != null && userMeals.remove(id) != null;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        // ветка сохранения новой еды
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (userMeals == null) {
                userMeals = new ConcurrentHashMap<>();
                userMeals.put(meal.getId(), meal);
                repository.put(userId, userMeals);
            } else {
                userMeals.put(meal.getId(), meal);
            }
            return meal;
        }
        // ветка обновления еды
        return userMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }
}

