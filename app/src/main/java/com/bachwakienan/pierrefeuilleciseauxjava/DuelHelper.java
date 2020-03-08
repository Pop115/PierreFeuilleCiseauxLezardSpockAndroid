package com.bachwakienan.pierrefeuilleciseauxjava;

public class DuelHelper {

    /**
     * Check if shape 0 is winner
     *
     * @param shape0 shape of the first player
     * @param shape1 shape of the second player
     * @return -1 if shape 0 loses, 0 if tie, 1 if shape 0 wins
     */
    static int hasShape0Won(Shape shape0, Shape shape1) {

        if (shape0 == shape1) {
            return 0;
        } else if (shape0 == Shape.SCISSORS) {
            if (shape1 == Shape.PAPER || shape1 == Shape.LIZARD)
                return 1;

        } else if (shape0 == Shape.PAPER) {
            if (shape1 == Shape.ROCK || shape1 == Shape.SPOCK)
                return 1;

        } else if (shape0 == Shape.ROCK) {
            if (shape1 == Shape.LIZARD || shape1 == Shape.SCISSORS)
                return 1;

        } else if (shape0 == Shape.LIZARD) {
            if (shape1 == Shape.SPOCK || shape1 == Shape.PAPER)
                return 1;

        } else if (shape0 == Shape.SPOCK) {
            if (shape1 == Shape.SCISSORS || shape1 == Shape.ROCK)
                return 1;
        }

        return -1;

    }

}
