package exercitiul1;

import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
public class PersonReportGenerator {
    private final PersonProvider provider;
    private final List<AgeRange> ranges;

    public void generateReport(){
        generateReport(provider.getPersons());
    }

    private void generateReport(List<Person> persons) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
           Map<AgeRange, List<String>> groups =  persons.stream()
                   .collect(groupingBy(this::getPersonsByAgeRange, mapping(p -> p.fName() + " " + p.lName(), toList())));
           groups.entrySet().stream().sorted(Comparator.comparingInt(r -> r.getKey().minAge())).forEach(ageRange -> {
               try {
                   writeLine(writer,ageRange.getKey().ageLabel(),ageRange.getValue());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void writeLine(BufferedWriter writer, String ageLabel, List<String> names) throws IOException {
        writer.write("%s - %s".formatted(ageLabel, String.join(",", names)));
        writer.newLine();
    }

    private AgeRange getPersonsByAgeRange(Person person) {
        return ranges.stream().filter(range -> range.minAge() < person.age() && range.maxAge() > person.age())
                .findFirst()
                .orElse(new AgeRange(0,0,"Others"));
    }
}
