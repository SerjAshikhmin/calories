package ru.ashihmin.calories.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.ashihmin.calories.model.User;
import ru.ashihmin.calories.service.UserService;
import ru.ashihmin.calories.to.UserTo;
import ru.ashihmin.calories.util.UserUtil;

import java.util.List;

import static ru.ashihmin.calories.util.ValidationUtil.assureIdConsistent;
import static ru.ashihmin.calories.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public List<User> getAll() {
        log.debug("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.debug("get {}", id);
        return service.get(id);
    }

    public User create(UserTo userTo) {
        log.info("create from to {}", userTo);
        return create(UserUtil.createNewFromTo(userTo));
    }

    public User create(User user) {
        log.debug("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.debug("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.debug("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        service.update(userTo);
    }

    public User getByMail(String email) {
        log.debug("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }
}