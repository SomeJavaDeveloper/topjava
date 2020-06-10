package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MealServiceDAO implements MealService{

    private Map<Integer, Meal> mealMap = new HashMap<Integer, Meal>();
    
    private AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public List<Meal> getAllMeals() {
        for (Map.Entry entry : mealMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
        return new ArrayList<>(mealMap.values());
    }

    @Override
    public Meal getMealById(Integer id) {
        return mealMap.get(id);
    }


    @Override
    public void addMeal(LocalDateTime time, String description, int calories) {
        Integer id = currentId.addAndGet(1);
        Meal mealToAdd = new Meal(time, description, calories);
        mealToAdd.setId(id);
        mealMap.put(id, mealToAdd);
    }

    @Override
    public void updateMeal(Integer id, LocalDateTime time, String description, int calories) {
        Meal mealToAdd = new Meal(time, description, calories);
        mealToAdd.setId(id);
        mealMap.put(id, mealToAdd);
    }

    @Override
    public void deleteMeal(Integer mealId) {
        mealMap.remove(mealId);
    }
}
