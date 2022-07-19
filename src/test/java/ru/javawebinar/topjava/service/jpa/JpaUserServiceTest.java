package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import static ru.javawebinar.topjava.Profiles.HIBERNATE_CASH_DISABLED;
import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles({JPA, HIBERNATE_CASH_DISABLED})
public class JpaUserServiceTest extends AbstractUserServiceTest {
}