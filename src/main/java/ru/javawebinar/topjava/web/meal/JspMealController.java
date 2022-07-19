package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    protected JspMealController(MealService service) {
        super(service);
    }

    @GetMapping
    public String getAll(Model model) {
        log.info("getAll for user {}", SecurityUtil.authUserId());
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/filter")
    public String getBetween(Model model, @RequestParam String startTime,
                             @RequestParam String endTime,
                             @RequestParam String startDate,
                             @RequestParam String endDate
    ) {
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", parseLocalDate(startDate),
                parseLocalDate(endDate), parseLocalTime(startTime), parseLocalTime(endTime), SecurityUtil.authUserId());
        model.addAttribute("meals", getBetween(parseLocalDate(startDate),
                parseLocalTime(startTime), parseLocalDate(endDate), parseLocalTime(endTime)));
        return "meals";
    }

    @GetMapping("/add")
    public String add(Model model) {
        log.info("add new meal for user {}", SecurityUtil.authUserId());
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "mealForm";
    }

    @GetMapping("/{id}")
    public String get(Model model, @PathVariable Integer id) {
        log.info("get meal {} for user {}", id, SecurityUtil.authUserId());
        model.addAttribute("meal", get(id));
        return "mealForm";
    }

    @GetMapping("/delete/{id}")
    public String exorcise(@PathVariable int id) {
        log.info("delete meal {} for user {}", id, SecurityUtil.authUserId());
        delete(id);
        return "redirect:/meals";
    }

    @PostMapping("/update")
    public String createOrUpdate(@RequestParam String id,
                                 @RequestParam String calories,
                                 @RequestParam String dateTime,
                                 @RequestParam String description
    ) {
        Meal meal = new Meal(LocalDateTime.parse(dateTime),
                description, Integer.parseInt(calories));
        if (StringUtils.hasLength(id)) {
            log.info("update {} for user {}", meal, SecurityUtil.authUserId());
            update(meal, Integer.parseInt(id));
        } else {
            log.info("create {} for user {}", meal, SecurityUtil.authUserId());
            create(meal);
        }
        return "redirect:/meals";
    }
}