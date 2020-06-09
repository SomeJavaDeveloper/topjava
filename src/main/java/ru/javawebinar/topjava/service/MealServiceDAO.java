package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MealServiceDAO implements MealService{

    //private
    public static Map<Integer, Meal> mealMap = new HashMap<Integer, Meal>();
    
    private AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(mealMap.values());
    }

    @Override
    public void addMeal(LocalDateTime time, String description, int calories) {
        Integer id = currentId.addAndGet(1);
        //Добавить проверку на ошибки
        Meal mealToAdd = new Meal(time, description, calories);
        mealToAdd.setId(id);
        mealMap.put(id, mealToAdd);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public void deleteMeal(Integer mealId) {
//        mealMap.remove(mealId);
        mealMap.clear();
    }
}
