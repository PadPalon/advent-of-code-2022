package ch.neukom.advent2022.day13;

import java.util.List;

public final class NumberPart implements DataPart {

    private final Integer value;

    public NumberPart(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public int compareTo(DataPart other) {
        if (other instanceof NumberPart numberPart) {
            return Integer.compare(value, numberPart.getValue());
        } else if (other instanceof ListPart listPart) {
            return new ListPart(List.of(this)).compareTo(listPart);
        } else {
            throw new IllegalStateException("Unknown data part");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
