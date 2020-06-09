package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MealService {

    public Collection<Meal> getAllMeals();

    public void addMeal(LocalDateTime time, String description, int calories);

    public void updateMeal(Meal meal);

    public void deleteMeal(Integer mealId);
}
