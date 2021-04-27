package ru.ashihmin.calories.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.ashihmin.calories.service.AbstractUserServiceTest;

import static ru.ashihmin.calories.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaUserServiceTest extends AbstractUserServiceTest {
}