package ru.ashihmin.calories.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ashihmin.calories.model.Meal;
import ru.ashihmin.calories.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.ashihmin.calories.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private static final Logger log = LoggerFactory.getLogger(MealService.class);

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int userId) {
        log.debug("get meal {} for user {}", id, userId);
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        log.debug("delete meal {} for user {}", id, userId);
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Meal> getBetweenDates(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        log.debug("getBetweenDates dates({} - {}) for user {}", startDate, endDate, userId);
        return repository.getBetweenInclusive(startDate, endDate, userId);
    }

    public List<Meal> getAll(int userId) {
        log.debug("getAll for user {}", userId);
        return repository.getAll(userId);
    }

    public void update(Meal meal, int userId) {
        log.debug("update {} for user {}", meal, userId);
        Assert.notNull(meal, "meal must not be null");
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public Meal create(Meal meal, int userId) {
        log.debug("create {} for user {}", meal, userId);
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, userId);
    }

    public Meal getWithUser(int id, int userId) {
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }
}