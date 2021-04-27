package ru.ashihmin.calories.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.ashihmin.calories.UserTestData;
import ru.ashihmin.calories.model.Meal;
import ru.ashihmin.calories.service.AbstractMealServiceTest;
import ru.ashihmin.calories.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.ashihmin.calories.MealTestData.*;
import static ru.ashihmin.calories.Profiles.DATAJPA;
import static ru.ashihmin.calories.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
class DataJpaMealServiceTest extends AbstractMealServiceTest {
    @Test
    void getWithUser() throws Exception {
        Meal adminMeal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MEAL_MATCHERS.assertMatch(adminMeal, ADMIN_MEAL1);
        UserTestData.USER_MATCHERS.assertMatch(adminMeal.getUser(), UserTestData.ADMIN);
    }

    @Test
    void getWithUserNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.getWithUser(1, ADMIN_ID));
    }
}
