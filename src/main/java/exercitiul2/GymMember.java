package exercitiul2;

import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;

public class GymMember {
    private final String name;
    private final LocalDate birthDate;
    private final Duration subscription;
    private int subscriptionAshours;

    public GymMember(String name, LocalDate birthDate, Duration subscription) {
        this.name = name;
        this.birthDate = birthDate;
        this.subscription = subscription;
        this.subscriptionAshours = (int) subscription.toHours();
    }


    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Duration getSubscription() {
        return Duration.ofHours(subscriptionAshours);
    }

    @Override
    public String toString() {
        return "GymMember{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", subscription=" + getSubscription() +
                '}';
    }

    public int getAge(){
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public void addTimeToSubscription(int hours) {
        subscriptionAshours +=  hours;
    }

    public void substractHoursFromSubscription(int hoursSpent) {
        subscriptionAshours -= hoursSpent;
    }
}
