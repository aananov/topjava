package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 2));
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        try {
            return repository.get(userId).values().stream().sorted(
                    Comparator.comparing(Meal::getDateTime).reversed().thenComparing(Meal::getDescription)).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Collection<Meal> getAllBetweenDates(int userID, LocalDate startDate, LocalDate endDate) {
        return getAll(userID).stream().filter(
                meal -> DateTimeUtil.isBetweenTwoDates(meal.getDate(), startDate, endDate)).collect(Collectors.toList());
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId) == null ? null : repository.get(userId).get(id);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (get(id, userId) == null) {
            return false;
        }
        return repository.get(userId) != null && repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            return addToExistingUserOrCreateNewUserMapping(meal, userId);
        }
        if (repository.get(userId) == null || !repository.get(userId).containsKey(meal.getId())) return null;
        return addToExistingUserOrCreateNewUserMapping(meal, userId);
    }

    private Meal addToExistingUserOrCreateNewUserMapping(Meal meal, int userId) {
        if (repository.containsKey(userId)) {
            repository.get(userId).put(meal.getId(), meal);
        } else {
            Map<Integer, Meal> newUserMeals = new ConcurrentHashMap<>();
            newUserMeals.put(meal.getId(), meal);
            repository.put(userId, newUserMeals);
        }
        return meal;
    }

}

