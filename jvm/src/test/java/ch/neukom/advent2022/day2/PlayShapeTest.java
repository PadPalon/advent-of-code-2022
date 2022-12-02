package ch.neukom.advent2022.day2;

import org.testng.annotations.*;

import static com.google.common.truth.Truth.*;

public class PlayShapeTest {
    @Test(dataProvider = "getCombinations")
    public void testWinningCombinations(PlayShape winner, PlayShape loser) {
        assertThat(winner.winsAgainst(loser)).isTrue();
    }

    @Test(dataProvider = "getCombinations")
    public void testGetWeakness(PlayShape winner, PlayShape loser) {
        assertThat(loser.getWeakness()).isEqualTo(winner);
    }

    @Test(dataProvider = "getCombinations")
    public void testLosingCombinations(PlayShape winner, PlayShape loser) {
        assertThat(loser.winsAgainst(winner)).isFalse();
    }

    @Test(dataProvider = "getCombinations")
    public void testGetStrength(PlayShape winner, PlayShape loser) {
        assertThat(winner.getStrength()).isEqualTo(loser);
    }

    @DataProvider
    public Object[][] getCombinations() {
        return new Object[][] {
            new Object[] {
                PlayShape.ROCK, PlayShape.SCISSORS
            },
            new Object[] {
                PlayShape.SCISSORS, PlayShape.PAPER
            },
            new Object[] {
                PlayShape.PAPER, PlayShape.ROCK
            }
        };
    }
}
