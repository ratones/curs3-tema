package exercitiul1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;

public class RangeGenerator {
    public List<AgeRange> generateRanges(int... values){
        List<AgeRange> ranges = new ArrayList<>();
        List<Integer> rangeValues = new ArrayList<>();
        if(values != null) {
            rangeValues.addAll(Arrays.stream(values).boxed().toList());
        }
        rangeValues.add(0);
        rangeValues.add(MAX_VALUE);
        rangeValues = rangeValues.stream().distinct().sorted().toList();
        for (int i = 0; i < rangeValues.size() - 1; i++) {
            ranges.add(new AgeRange(rangeValues.get(i), rangeValues.get(i+1), "%s - %s".formatted(rangeValues.get(i),rangeValues.get(i+1))));
        }
        return ranges;
    }
}
