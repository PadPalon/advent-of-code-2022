package ch.neukom.advent2022.day13;

public sealed interface DataPart extends Comparable<DataPart> permits ListPart, NumberPart {
}
