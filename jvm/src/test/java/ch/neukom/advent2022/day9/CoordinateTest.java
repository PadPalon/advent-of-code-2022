package ch.neukom.advent2022.day9;

import org.testng.annotations.*;

import static com.google.common.truth.Truth.*;

public class CoordinateTest {
    @Test
    public void testDiagonalDistance() {
        Coordinate first = new Coordinate(0, 0);
        Coordinate second = new Coordinate(1, 1);
        assertThat(first.distance(second)).isEqualTo(1);
    }

    @Test
    public void testLongDiagonalDistance() {
        Coordinate first = new Coordinate(0, 0);
        Coordinate second = new Coordinate(2, 3);
        assertThat(first.distance(second)).isEqualTo(3);
    }

    @Test
    public void testLongDiagonalNegativeDistance() {
        Coordinate first = new Coordinate(3, 2);
        Coordinate second = new Coordinate(-1, -1);
        assertThat(first.distance(second)).isEqualTo(4);
    }

    @Test
    public void testXDistance() {
        Coordinate first = new Coordinate(0, 0);
        Coordinate second = new Coordinate(4, 0);
        assertThat(first.distance(second)).isEqualTo(4);
    }

    @Test
    public void testYDistance() {
        Coordinate first = new Coordinate(0, 0);
        Coordinate second = new Coordinate(0, 4);
        assertThat(first.distance(second)).isEqualTo(4);
    }

    @Test
    public void testMoveTowardsX() {
        Coordinate first = new Coordinate(0, 0);
        Coordinate second = new Coordinate(4, 0);
        assertThat(first.moveTowards(second)).isEqualTo(new Coordinate(1, 0));
    }

    @Test
    public void testMoveTowardsY() {
        Coordinate first = new Coordinate(0, 0);
        Coordinate second = new Coordinate(0, 4);
        assertThat(first.moveTowards(second)).isEqualTo(new Coordinate(0, 1));
    }

    @Test
    public void testMoveTowardsDiagonal() {
        Coordinate first = new Coordinate(-1, -1);
        Coordinate second = new Coordinate(3, 2);
        assertThat(first.moveTowards(second)).isEqualTo(new Coordinate(0, 0));
    }
}
