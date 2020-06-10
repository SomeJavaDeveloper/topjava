package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealServiceDAO;
import ru.javawebinar.topjava.util.MealsUtil;

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

    private final MealServiceDAO serviceDAO = new MealServiceDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("listMeal")){

            List<MealTo> mealToList = MealsUtil.filteredByStreams(serviceDAO.getAllMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
            req.setAttribute("mealToList", mealToList);
            getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);

        } else if (action.equalsIgnoreCase("delete")){

            serviceDAO.deleteMeal(Integer.valueOf(req.getParameter("mealId")));
            List<MealTo> mealToList = MealsUtil.filteredByStreams(serviceDAO.getAllMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
            req.setAttribute("mealToList", mealToList);
            getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);

        } else if(action.equalsIgnoreCase("add")){

            getServletContext().getRequestDispatcher("/mealEditAdd.jsp").forward(req, resp);

        } else if(action.equalsIgnoreCase("update")){

            Meal mealToUpdate = serviceDAO.getMealById(Integer.valueOf(req.getParameter("mealId")));
            req.setAttribute("meal", mealToUpdate);
            getServletContext().getRequestDispatcher("/mealEditAdd.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalDateTime time = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        if(req.getParameter("mealId").equals("")){
            serviceDAO.addMeal(time, description, calories);
        } else {
            serviceDAO.updateMeal(Integer.valueOf(req.getParameter("mealId")), time, description, calories);
        }

        List<MealTo> mealToList = MealsUtil.filteredByStreams(serviceDAO.getAllMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        req.setAttribute("mealToList", mealToList);
        getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
