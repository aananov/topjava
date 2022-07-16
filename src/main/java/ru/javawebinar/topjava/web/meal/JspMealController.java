package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.AbstractMealController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {

    protected JspMealController(MealService service) {
        super(service);
    }

    @GetMapping
    public String getAllJsp(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/filter")
    public String getBetweenJsp(Model model, @RequestParam String startTime,
                                @RequestParam String endTime,
                                @RequestParam String startDate,
                                @RequestParam String endDate
    ) {
        model.addAttribute("meals", getBetween(parseLocalDate(startDate),
                parseLocalTime(startTime), parseLocalDate(endDate), parseLocalTime(endTime)));
        return "meals";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        model.addAttribute("action", "create");
        return "mealForm";
    }

    @GetMapping("/{id}")
    public String getJsp(Model model, @PathVariable Integer id) {
        model.addAttribute("meal", get(id));
        model.addAttribute("action", "update");
        return "mealForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteJsp(@PathVariable int id) {
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
            update(meal, Integer.parseInt(id));
        } else {
            create(meal);
        }
        return "redirect:/meals";
    }
}