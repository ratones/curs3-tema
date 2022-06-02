package exercitiul2;

import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class Gym {
    private final List<GymMember> members;

    public void registerMember(GymMember member){
        this.members.add(member);
    }

    public Optional<GymMember> getMemberInfoByName(String name){
        return members.stream().filter(gymMember -> gymMember.getName().equalsIgnoreCase(name)).findFirst();
    }

    public double avgAgeOfMembers(){
        return Math.round(members.stream().collect(averagingInt(GymMember::getAge)));
    }

    public int maxAgeOfMembers(){
        return members.stream().map(GymMember::getAge).max(Integer::compare).orElse(0);
    }

    public int minAgeOfMembers() {
        return members.stream().map(GymMember::getAge).min(Integer::compare).orElse(0);
    }

    public String getTotalTimeRemaining(){
        double hours = members.stream().mapToDouble(d -> d.getSubscription().toHours()).sum();
        return "Total time for members subscriptios: %s hours.".formatted(hours);
    }

    public void addTimeToMember(String memberName, int hours){
        if(hours > 0) {
            getMemberInfoByName(memberName).ifPresent(m -> m.addTimeToSubscription(hours));
        }
    }

    public void checkoutMember(String memberName, int hoursSpent){
        if(hoursSpent > 0) {
            getMemberInfoByName(memberName).ifPresent(m -> m.substractHoursFromSubscription(hoursSpent));
        }
    }

    public void generateReport(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("remaining-time-report-%s.txt".formatted(LocalDate.now())))){
            Map<String, List<String>> notifyMembers = members.stream()
                    .collect(groupingBy(Gym::groupMembersBySubscriptionExpires,mapping(GymMember::getName,toList())));
            writeReport(writer, notifyMembers);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void writeReport(BufferedWriter writer, Map<String, List<String>> notifyMembers) {
        notifyMembers.forEach((state, names) -> {
            try {
                writer.write("%s - %s".formatted(state, String.join(",",names)));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static String groupMembersBySubscriptionExpires(GymMember member) {
        long remainingHours = member.getSubscription().toHours();
        if( remainingHours < 10){
            return SubscriptionStates.RED.name();
        }else if(remainingHours < 30){
            return SubscriptionStates.YELLOW.name();
        }else {
            return SubscriptionStates.GREEN.name();
        }
    }

}

enum SubscriptionStates{
    RED,
    YELLOW,
    GREEN
}
