package ch.neukom.advent2022.day13;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.base.Strings;

public class Part2 {
    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        ListPart firstDivider = new ListPart(new ListPart(List.of(new NumberPart(2))));
        ListPart secondDivider = new ListPart(new ListPart(List.of(new NumberPart(6))));
        Stream<ListPart> inputPartStream = reader.readInput()
            .filter(line -> !Strings.isNullOrEmpty(line))
            .map(Util::parsePart);
        List<ListPart> sortedParts = Stream.concat(
                inputPartStream,
                Stream.of(firstDivider, secondDivider)
            )
            .sorted()
            .toList();
        int decoderKey = (sortedParts.indexOf(firstDivider) + 1) * (sortedParts.indexOf(secondDivider) + 1);
        System.out.println("The decoder key is $decoderKey");
    }
}
