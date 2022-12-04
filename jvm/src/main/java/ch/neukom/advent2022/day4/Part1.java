package ch.neukom.advent2022.day4;

import java.io.IOException;

import ch.neukom.advent2022.day4.SectionAssignment.Section;
import ch.neukom.advent2022.util.InputResourceReader;
import com.google.common.base.Splitter;

public class Part1 {
    private static final Splitter COMMA_SPLITTER = Splitter.on(',').omitEmptyStrings().trimResults();

    public static void main(String[] args) throws IOException {
        try (InputResourceReader reader = new InputResourceReader(Part1.class)) {
            run(reader);
        }
    }

    private static void run(InputResourceReader reader) {
        long fullyCollapsedAssignment = reader.readInput()
            .map(line ->
                COMMA_SPLITTER.splitToStream(line)
                    .map(part -> part.split("-"))
                    .map(Section::create)
                    .collect(SectionAssignment::new, SectionAssignment::addSection, SectionAssignment::merge)
            )
            .filter(SectionAssignment::doesAssignmentReduceToSingleSection)
            .count();
        System.out.println("$fullyCollapsedAssignment assignments can be fully collapsed to a single section");
    }
}
