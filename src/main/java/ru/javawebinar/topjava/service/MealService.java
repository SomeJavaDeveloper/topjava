package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MealService {

    public Collection<Meal> getAllMeals();

    public Meal getMealById(Integer id);

    public void addMeal(LocalDateTime time, String description, int calories);

    public void updateMeal(Integer id, LocalDateTime time, String description, int calories);

    public void deleteMeal(Integer mealId);
}
