package ru.ashihmin.calories.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.ashihmin.calories.MealTestData;
import ru.ashihmin.calories.model.User;
import ru.ashihmin.calories.service.AbstractUserServiceTest;
import ru.ashihmin.calories.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.ashihmin.calories.Profiles.DATAJPA;
import static ru.ashihmin.calories.UserTestData.*;

@ActiveProfiles(DATAJPA)
class DataJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    void getWithMeals() throws Exception {
        User admin = service.getWithMeals(ADMIN_ID);
        USER_MATCHERS.assertMatch(admin, ADMIN);
        MealTestData.MEAL_MATCHERS.assertMatch(admin.getMeals(), MealTestData.ADMIN_MEAL2, MealTestData.ADMIN_MEAL1);
    }

    @Test
    void getWithMealsNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.getWithMeals(1));
    }
}