package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController extends AbstractMealController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    protected MealRestController(MealService service) {
        super(service);
    }

    @Override
    public Meal get(int id) {
        log.info("get meal {} for user {}", id, SecurityUtil.authUserId());
        return super.get(id);
    }

    @Override
    public void delete(int id) {
        log.info("delete meal {} for user {}", id, SecurityUtil.authUserId());
        super.delete(id);
    }

    @Override
    public List<MealTo> getAll() {
        log.info("getAll for user {}", SecurityUtil.authUserId());
        return super.getAll();
    }

    @Override
    public Meal create(Meal meal) {
        log.info("create {} for user {}", meal, SecurityUtil.authUserId());
        return super.create(meal);
    }

    @Override
    public void update(Meal meal, int id) {
        log.info("update {} for user {}", meal, SecurityUtil.authUserId());
        super.update(meal, id);
    }

    @Override
    public List<MealTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                   @Nullable LocalDate endDate, @Nullable LocalTime endTime) {
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, SecurityUtil.authUserId());
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}