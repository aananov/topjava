package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private final MealDao mealDao = new MealDaoMemoryImpl();

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = req.getParameter("action");
        if (action == null) {
            List<MealTo> mealTos = MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
            req.setAttribute("mealTos", mealTos);
            RequestDispatcher view = req.getRequestDispatcher("/meals.jsp");
            view.forward(req, resp);
        }
        if (action != null) {
            if (action.equals("delete")) {
                Meal mealToDelete = mealDao.getById(Integer.parseInt(req.getParameter("id")));
                mealDao.delete(mealToDelete);
                resp.sendRedirect("meals");
            }
            if (action.equals("update")) {
                Meal mealToUpdate = mealDao.getById(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("mealToUpdate", mealToUpdate);
                RequestDispatcher view = req.getRequestDispatcher("/update.jsp");
                view.forward(req, resp);
            }
            if (action.equals("create")) {
                resp.sendRedirect("update.jsp");
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to create/update form");
        req.setCharacterEncoding("UTF-8");
        Integer mealID = (req.getParameter("mealID") != null && !req.getParameter("mealID").equals("")) ? Integer.parseInt(req.getParameter("mealID")) : null;
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateTime"), dtf);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        if (mealID != null) {
            mealDao.update(mealDao.getById(mealID), localDateTime, description, calories);
        } else {
            mealDao.create(localDateTime, description, calories);
        }
        resp.sendRedirect("meals");
    }
}
