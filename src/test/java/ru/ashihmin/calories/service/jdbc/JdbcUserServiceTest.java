package ru.ashihmin.calories.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.ashihmin.calories.service.AbstractUserServiceTest;

import static ru.ashihmin.calories.Profiles.JDBC;

@ActiveProfiles(JDBC)
class JdbcUserServiceTest extends AbstractUserServiceTest {
}