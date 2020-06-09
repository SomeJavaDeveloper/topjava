package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealServiceDAO;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        MealServiceDAO serviceDAO = new MealServiceDAO();

        //Test
        serviceDAO.addMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        serviceDAO.addMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        serviceDAO.addMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        serviceDAO.addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        serviceDAO.addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        serviceDAO.addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        serviceDAO.addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

//        String action = req.getParameter("action");

//        if (action.equalsIgnoreCase("listMeal")){
//            List<MealTo> mealToList = MealsUtil.filteredByStreams(serviceDAO.getAllMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
//            req.setAttribute("mealToList", mealToList);
//            getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);
//        } if (action.equalsIgnoreCase("delete")){
//            int mealId = Integer.parseInt(req.getParameter("mealId"));
//            serviceDAO.deleteMeal(mealId);
//
//            List<MealTo> mealToList = MealsUtil.filteredByStreams(serviceDAO.getAllMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
//            req.setAttribute("mealToList", mealToList);
//            getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);
//        }

        List<MealTo> mealToList = MealsUtil.filteredByStreams(serviceDAO.getAllMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        req.setAttribute("mealToList", mealToList);
//        getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);

        RequestDispatcher view = req.getRequestDispatcher("/meals.jsp");
        view.forward(req, resp);
    }
}
