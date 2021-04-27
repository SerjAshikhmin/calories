package ru.ashihmin.calories;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ashihmin.calories.to.MealTo;
import ru.ashihmin.calories.web.meal.MealRestController;
import ru.ashihmin.calories.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.ashihmin.calories.TestUtil.mockAuthorize;
import static ru.ashihmin.calories.UserTestData.USER;

public class SpringMain {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.getAll();
            System.out.println();

            mockAuthorize(USER);

            MealRestController mealController = appCtx.getBean(MealRestController.class);
            List<MealTo> filteredMealsWithExcess =
                    mealController.getBetween(
                            LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
            filteredMealsWithExcess.forEach(System.out::println);
        }
    }
}
