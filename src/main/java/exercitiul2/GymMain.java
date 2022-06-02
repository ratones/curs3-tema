package exercitiul2;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class GymMain {
    public static void main(String[] args) {
        List<GymMember> members =List.of(
                new GymMember("Cristi", LocalDate.of(1973,9,9), Duration.ofDays(10)),
                new GymMember("Alex", LocalDate.of(1982,2,3), Duration.ofDays(1)),
                new GymMember("Mihai", LocalDate.of(1953,3,19), Duration.ofDays(5)),
                new GymMember("Marius", LocalDate.of(1985,6,12), Duration.ofDays(12)),
                new GymMember("Andrei", LocalDate.of(1978,1,1), Duration.ofDays(6)),
                new GymMember("Ion", LocalDate.of(1996,7,12), Duration.ofDays(10))
        );

        Gym gym = new Gym(members);

        System.out.println("Oldest member age: %s".formatted(gym.maxAgeOfMembers()));
        System.out.println("Average members age: %s".formatted(gym.avgAgeOfMembers()));
        System.out.println("Youngest member age: %s".formatted(gym.minAgeOfMembers()));
        System.out.println("Member with name Cristi: %s".formatted(gym.getMemberInfoByName("Cristi")));
        System.out.println("Subscription time: %s".formatted(gym.getTotalTimeRemaining()));
        System.out.println("==== Adding time to Cristi");
        gym.addTimeToMember("Cristi", 20);
        System.out.println("Member with name Cristi after added subscription: %s".formatted(gym.getMemberInfoByName("Cristi")));
        gym.checkoutMember("Alex", 20);
        gym.checkoutMember("Andrei", 120);
        gym.generateReport();
        System.out.println("Report generated!");
    }
}
