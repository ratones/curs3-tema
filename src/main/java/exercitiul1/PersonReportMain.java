package exercitiul1;

import java.util.List;

public class PersonReportMain {
    public static void main(String[] args) {
        RangeGenerator gen = new RangeGenerator();
        List<AgeRange> ranges = gen.generateRanges(0,20,15,30, 60, 75);
        System.out.println(ranges);
        PersonReportGenerator generator = new PersonReportGenerator(new FilePersonProvider("src/main/resources/people.txt"),ranges);
        generator.generateReport();
    }
}
