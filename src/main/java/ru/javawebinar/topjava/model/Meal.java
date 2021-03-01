package ru.javawebinar.topjava.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE,    query = "DELETE FROM Meal m WHERE m.id=:id and m.user.id=:user_id"),
        @NamedQuery(name = Meal.ALL_MEALS, query = "SELECT m FROM Meal m where m.user.id=:user_id order by m.dateTime desc"),
        @NamedQuery(name = Meal.GET,         query = "SELECT m FROM Meal m where m.id=:id and m.user.id=:user_id"),
        @NamedQuery(name = Meal.GET_BETWEEN, query = "SELECT m FROM Meal m where m.user.id=:user_id and m.dateTime >= :dt_start and m.dateTime < :dt_end order by m.dateTime desc"),
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Meal extends AbstractBaseEntity {

    public static final String DELETE    = "Meal.delete";
    public static final String ALL_MEALS = "Meal.getAllMeals";
    public static final String GET         = "Meal.get";
    public static final String GET_BETWEEN = "Meal.getBetween";

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    private String description;

    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
