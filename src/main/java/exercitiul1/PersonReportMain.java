package exercitiul1;

import java.util.List;

public class PersonReportMain {
    public static void main(String[] args) {
        List<AgeRange> ranges = List.of(
                new AgeRange(1,30,"1-30"),
                new AgeRange(31, 50, "30-50"),
                new AgeRange(51, 65, "50-65"),
                new AgeRange(66, 200,"Seniors")
        );
        PersonReportGenerator generator = new PersonReportGenerator(new FilePersonProvider("src/main/resources/people.txt"),ranges);
        generator.generateReport();
    }
}
