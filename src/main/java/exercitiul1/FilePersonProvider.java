package exercitiul1;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
public class FilePersonProvider implements PersonProvider{

    private final String inputSource;

    @Override
    public List<Person> getPersons() {
        try {
            return Files.lines(Path.of(inputSource)).map(this::buildPerson).toList();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private Person buildPerson(String line) {
        String[] parts = line.split(",");
        return new Person(parts[0], parts[1], Integer.parseInt(parts[2]));
    }
}
