package ru.ashihmin.calories.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ashihmin.calories.model.Meal;
import ru.ashihmin.calories.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.ashihmin.calories.MealTestData.*;
import static ru.ashihmin.calories.UserTestData.ADMIN_ID;
import static ru.ashihmin.calories.UserTestData.USER_ID;

public abstract class AbstractMealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Test
    void delete() throws Exception {
        service.delete(MEAL1_ID, USER_ID);
        assertThrows(NotFoundException.class, () ->
                service.get(MEAL1_ID, USER_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1, USER_ID));
    }

    @Test
    void deleteNotOwn() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(MEAL1_ID, ADMIN_ID));
    }

    @Test
    void create() throws Exception {
        Meal newMeal = getNew();

        Meal created = service.create(newMeal, USER_ID);
        created.setUser(null);
        Integer newId = created.getId();
        newMeal.setId(newId);
        Meal actual = service.get(newId, USER_ID);
        actual.setUser(null);

        MEAL_MATCHERS.assertMatch(created, newMeal);
        MEAL_MATCHERS.assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    void get() throws Exception {
        Meal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);

        MEAL_MATCHERS.assertMatch(actual, ADMIN_MEAL1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1, ADMIN_ID));
    }

    @Test
    void getNotOwn() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(MEAL1_ID, ADMIN_ID));
    }

    @Test
    void update() throws Exception {
        Meal updated = getUpdated();

        service.update(updated, USER_ID);
        Meal meal = service.get(MEAL1_ID, USER_ID);

        MEAL_MATCHERS.assertMatch(meal, updated);
    }

    @Test
    void updateNotFound() throws Exception {
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(MEAL1, ADMIN_ID));
        assertEquals(e.getMessage(), "Not found entity with id=" + MEAL1_ID);
    }

    @Test
    void getAll() throws Exception {
        List<Meal> meals = service.getAll(USER_ID);

        MEAL_MATCHERS.assertMatch(meals, MEALS);
    }

    @Test
    void getBetween() throws Exception {
        List<Meal> meals = service.getBetweenDates(
                LocalDate.of(2020, Month.MAY, 30),
                LocalDate.of(2020, Month.MAY, 30), USER_ID);

        MEAL_MATCHERS.assertMatch(meals, MEAL3, MEAL2, MEAL1);
    }

    @Test
    void getBetweenWithNullDates() throws Exception {
        MEAL_MATCHERS.assertMatch(service.getBetweenDates(null, null, USER_ID), MEALS);
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(() -> service.create(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "  ", 300), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Meal(null, null, "Description", 300), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 9), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 5001), USER_ID), ConstraintViolationException.class);
    }
}