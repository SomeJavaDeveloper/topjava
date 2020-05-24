package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles

        List<UserMealWithExcess> mealWithExcesses = new ArrayList<>();
        List<UserMeal> mealInNeededTime = new ArrayList<>();
        LocalDateTime dayTime = null;
        int amountOfCalories = 0;

        for(UserMeal meal : meals){
            if(dayTime == null){
                dayTime = meal.getDateTime();
                amountOfCalories+=meal.getCalories();

                if(TimeUtil.isBetweenHalfOpen(LocalTime.from(meal.getDateTime()), startTime, endTime)){
                    mealInNeededTime.add(meal);
                }
            } else if(meal.getDateTime().getDayOfMonth() != dayTime.getDayOfMonth()){
                amountOfCalories = 0;
                dayTime = meal.getDateTime();
                amountOfCalories+=meal.getCalories();

                if(TimeUtil.isBetweenHalfOpen(LocalTime.from(meal.getDateTime()), startTime, endTime)){
                    mealInNeededTime.add(meal);
                }
            } else {
                amountOfCalories+=meal.getCalories();

                if(TimeUtil.isBetweenHalfOpen(LocalTime.from(meal.getDateTime()), startTime, endTime)){
                    mealInNeededTime.add(meal);
                }
            }

            if(amountOfCalories >  caloriesPerDay){
                for (UserMeal userMeal : mealInNeededTime) {
                    if (userMeal.getDateTime().getDayOfMonth() == dayTime.getDayOfMonth()
                            && userMeal.getDateTime().getMonth() == dayTime.getMonth()
                            && userMeal.getDateTime().getYear() == dayTime.getYear()) {
                        mealWithExcesses.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
                    }
                }
            }

        }

        return mealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams

        List<UserMealWithExcess> mealWithExcesses;
        AtomicInteger calories = new AtomicInteger();

        Map<Integer, List<UserMeal>> groupedByDayMap =
                meals.stream().collect(Collectors.groupingBy((UserMeal userMeal) -> userMeal.getDateTime().getDayOfMonth()));

        mealWithExcesses = groupedByDayMap.values().stream().peek(x -> {
            for(UserMeal m : x){
                calories.addAndGet(m.getCalories());
            }
            if(calories.intValue() > caloriesPerDay){
                x.removeIf(meal -> !TimeUtil.isBetweenHalfOpen(LocalTime.from(meal.getDateTime()), startTime, endTime));
            } else x.clear();
        }).flatMap(Collection::stream).collect(Collectors.toList()).
                stream().map(x -> new UserMealWithExcess(x.getDateTime(), x.getDescription(), x.getCalories(), true)).collect(Collectors.toList());

        return mealWithExcesses;
    }
}
