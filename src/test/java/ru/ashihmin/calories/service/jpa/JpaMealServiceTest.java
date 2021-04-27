package ru.ashihmin.calories.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.ashihmin.calories.service.AbstractMealServiceTest;

import static ru.ashihmin.calories.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaMealServiceTest extends AbstractMealServiceTest {
}