package ch.neukom.advent2022.day13;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Streams;

import static java.util.stream.Collectors.*;

public final class ListPart extends ArrayList<DataPart> implements DataPart {
    public ListPart(List<DataPart> parts) {
        this.addAll(parts);
    }

    @Override
    public int compareTo(DataPart other) {
        if (other instanceof NumberPart numberPart) {
            return this.compareTo(new ListPart(List.of(numberPart)));
        } else if (other instanceof ListPart listPart) {
            return Streams.zip(this.stream(), listPart.stream(), Comparable::compareTo)
                .filter(result -> result != 0)
                .findFirst()
                .orElseGet(() -> Integer.compare(this.size(), listPart.size()));
        } else {
            throw new IllegalStateException("Unknown data part");
        }
    }

    @Override
    public String toString() {
        return this.stream()
            .map(Object::toString)
            .collect(joining(", ", "[", "]"));
    }
}
