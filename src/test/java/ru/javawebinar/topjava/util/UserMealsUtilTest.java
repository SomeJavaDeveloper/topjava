package ru.javawebinar.topjava.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserMealsUtilTest {

    List<UserMeal> meals;

    @BeforeEach
    public void setUp() {
        meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
    }

    @Test
    void filteredByCyclesFindOne() {
        List<UserMealWithExcess> mealsTo = UserMealsUtil.filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        Assert.assertEquals(mealsTo.get(0), new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, true));
    }

    @Test
    void filteredByCyclesFindMany() {
        List<UserMealWithExcess> mealsTo = UserMealsUtil.filteredByCycles(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        Assert.assertEquals(mealsTo, Arrays.asList(
                new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, true),
                new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, true),
                new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, true),
                new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, true)
        ));
    }

    @Test
    void filteredByCyclesFindNone() {
        List<UserMealWithExcess> mealsTo = UserMealsUtil.filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(7, 1), 2000);
        Assert.assertEquals(mealsTo, new ArrayList<>());
    }

    @Test
    void filteredByStreamsOne() {
        List<UserMealWithExcess> mealsTo = UserMealsUtil.filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        Assert.assertEquals(mealsTo.get(0), new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, true));

    }

    @Test
    void filteredByStreamsMany() {
        List<UserMealWithExcess> mealsTo = UserMealsUtil.filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        Assert.assertEquals(mealsTo, Arrays.asList(
                new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, true),
                new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, true),
                new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, true),
                new UserMealWithExcess(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, true)
        ));
    }

    @Test
    void filteredByStreamsNone() {
        List<UserMealWithExcess> mealsTo = UserMealsUtil.filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(7, 1), 2000);
        Assert.assertEquals(mealsTo, new ArrayList<>());
    }
}