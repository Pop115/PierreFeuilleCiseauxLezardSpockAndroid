package com.bachwakienan.pierrefeuilleciseauxjava;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {



    /*
        Teste la sélection des formes des IA, les formes choisies étant aléatoires, je teste 1000 décisions
     */
    @Test
    public void computerChoose() {
        ComputerPlayer computerPlayer = new ComputerPlayer(1);
        List<Shape> normalShapes = Arrays.asList(Shape.PAPER, Shape.ROCK, Shape.SCISSORS);
        for (int i = 0; i < 1000; i++) {
            assertTrue(normalShapes.contains(computerPlayer.chooseShape()));
        }

        List<Shape> specialShapes = Arrays.asList(Shape.values());
        for (int i = 0; i < 1000; i++) {
            assertTrue(specialShapes.contains(computerPlayer.chooseShapeSpecial()));
        }
    }

    /*
    Partie la plus importante du jeu donc je teste toutes les combinaisons
     */
    @Test
    public void winAgainst() {
        assertEquals(0, DuelHelper.hasShape0Won(Shape.LIZARD, Shape.LIZARD));
        assertEquals(0, DuelHelper.hasShape0Won(Shape.SPOCK, Shape.SPOCK));
        assertEquals(0, DuelHelper.hasShape0Won(Shape.SCISSORS, Shape.SCISSORS));
        assertEquals(0, DuelHelper.hasShape0Won(Shape.PAPER, Shape.PAPER));
        assertEquals(0, DuelHelper.hasShape0Won(Shape.ROCK, Shape.ROCK));

        assertEquals(1, DuelHelper.hasShape0Won(Shape.ROCK, Shape.SCISSORS));
        assertEquals(-1, DuelHelper.hasShape0Won(Shape.ROCK, Shape.PAPER));

        assertEquals(1, DuelHelper.hasShape0Won(Shape.PAPER, Shape.ROCK));
        assertEquals(-1, DuelHelper.hasShape0Won(Shape.PAPER, Shape.SCISSORS));

        assertEquals(1, DuelHelper.hasShape0Won(Shape.SCISSORS, Shape.PAPER));
        assertEquals(-1, DuelHelper.hasShape0Won(Shape.SCISSORS, Shape.ROCK));

        assertEquals(1, DuelHelper.hasShape0Won(Shape.SCISSORS, Shape.PAPER));
        assertEquals(-1, DuelHelper.hasShape0Won(Shape.SCISSORS, Shape.ROCK));

        assertEquals(1, DuelHelper.hasShape0Won(Shape.SPOCK, Shape.SCISSORS));
        assertEquals(1, DuelHelper.hasShape0Won(Shape.SPOCK, Shape.ROCK));
        assertEquals(-1, DuelHelper.hasShape0Won(Shape.SPOCK, Shape.PAPER));
        assertEquals(-1, DuelHelper.hasShape0Won(Shape.SPOCK, Shape.LIZARD));

        assertEquals(1, DuelHelper.hasShape0Won(Shape.LIZARD, Shape.SPOCK));
        assertEquals(1, DuelHelper.hasShape0Won(Shape.LIZARD, Shape.PAPER));
        assertEquals(-1, DuelHelper.hasShape0Won(Shape.LIZARD, Shape.ROCK));
        assertEquals(-1, DuelHelper.hasShape0Won(Shape.LIZARD, Shape.SCISSORS));

    }
}