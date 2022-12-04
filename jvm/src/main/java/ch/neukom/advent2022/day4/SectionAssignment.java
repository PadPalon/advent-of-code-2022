package ch.neukom.advent2022.day4;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

public class SectionAssignment {
    private final List<Section> sections = Lists.newArrayList();

    public SectionAssignment addSection(Section section) {
        sections.add(section);
        Collections.sort(sections);
        return this;
    }

    public SectionAssignment merge(SectionAssignment other) {
        sections.addAll(other.getSections());
        Collections.sort(sections);
        return this;
    }

    public List<Section> getSections() {
        return sections;
    }

    public boolean doesAssignmentReduceToSingleSection() {
        if (sections.size() <= 1) {
            return true;
        } else {
            Section biggest = sections.first();
            return sections.stream()
                .filter(section -> !section.equals(biggest))
                .noneMatch(section -> section.startsBefore(biggest) || section.endsAfter(biggest));
        }
    }

    public boolean doesAnySectionOverlap() {
        if (sections.size() <= 1) {
            return false;
        } else {
            Multiset<Integer> sectionHits = HashMultiset.create();
            sections.stream()
                .flatMapToInt(section -> IntStream.rangeClosed(section.start(), section.end()))
                .forEach(sectionHits::add);
            return sectionHits.elementSet()
                .stream()
                .map(sectionHits::count)
                .anyMatch(count -> count > 1);
        }
    }

    public record Section(int start, int end) implements Comparable<Section> {
        public static Section create(String[] range) {
            int start;
            int end;
            if (range.length == 2) {
                start = Integer.parseInt(range[0]);
                end = Integer.parseInt(range[1]);
            } else {
                throw new IllegalArgumentException("Didn't provide start and end of section");
            }

            if (start > end) {
                throw new IllegalArgumentException("Start of section is after end");
            }

            return new Section(start, end);
        }

        public boolean startsBefore(Section other) {
            return this.start < other.start;
        }

        public boolean endsAfter(Section other) {
            return this.end > other.end;
        }

        @Override
        public int compareTo(Section other) {
            return Integer.compare(other.end - other.start, end - start);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Section section = (Section) o;

            if (start != section.start) return false;
            return end == section.end;
        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            return result;
        }
    }
}
