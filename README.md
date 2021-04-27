## Calorie tracking

Java Enterprise проект с регистрацией/авторизацией и правами доступа 
на основе ролей (USER, ADMIN). Администратор может создавать/редактировать/удалять 
пользователей, а пользователи - управлять своим профилем и данными (едой) через UI (по AJAX) 
и по REST интерфейсу с базовой авторизацией. Возможна фильтрация еды по датам и времени. 
Цвет записи таблицы еды зависит от того, превышает ли сумма калорий за день норму 
(редактируемый параметр в профиле пользователя). Весь REST интерфейс покрывается JUnit тестами, используя Spring MVC Test и Spring Security Test.

### Стек технологий:

Spring MVC, Spring Data JPA, Spring Security, Spring Security Test, Hibernate ORM, Hibernate Validator, SLF4J, Json Jackson, JSP, JSTL, Apache Tomcat, WebJars, DataTables plugin, PostgreSQL, JUnit, Hamcrest, jQuery, jQuery notification, Bootstrap.

### Инструкция по запуску:

- выбрать профиль в Maven(hsqldb/postgres);
- выбрать профили в spring-app.xml(hsqldb/postgres, jpa/datajpa/jdbc);
- в случае выбора postgres, создать БД с именем calories; при необходимости изменить username/password в postgres.properties и context.xml;
- создать переменную окружения CALORIES_ROOT со ссылкой на коренную папку проекта calories;
- создать конфигурацию Tomcat с базовым URL http://localhost:8080/calories/index и запустить