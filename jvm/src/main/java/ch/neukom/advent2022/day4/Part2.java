package ch.neukom.advent2022.day4;

import java.io.IOException;

import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.base.Splitter;

import static ch.neukom.advent2022.day4.SectionAssignment.*;

public class Part2 {
    private static final Splitter COMMA_SPLITTER = Splitter.on(',').omitEmptyStrings().trimResults();

    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part2.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        long overlappingAssignments = reader.readInput()
            .map(line ->
                COMMA_SPLITTER.splitToStream(line)
                    .map(part -> part.split("-"))
                    .map(Section::create)
                    .collect(SectionAssignment::new, SectionAssignment::addSection, SectionAssignment::merge)
            )
            .filter(SectionAssignment::doesAnySectionOverlap)
            .count();
        System.out.println("$overlappingAssignments assignments have overlapping sections");
    }
}
