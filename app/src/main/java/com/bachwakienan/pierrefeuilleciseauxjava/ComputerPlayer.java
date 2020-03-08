package com.bachwakienan.pierrefeuilleciseauxjava;

import java.util.Random;

public class ComputerPlayer {

    Random random;

    ComputerPlayer(){
        random = new Random();
    }

    ComputerPlayer(long seed){
        random = new Random(seed);
    }

    /*
        This one is a totally random bot but we could imagine a bot with predefined moves
        so the player could learn how to win against a particular bot and
        then he could fight against other different bots in different levels.
     */
    Shape chooseShape() {
        Shape[] authorizedShapes = {Shape.ROCK, Shape.PAPER, Shape.SCISSORS};
        return authorizedShapes[random.nextInt(authorizedShapes.length)];
    }

    //chooseShape but with special shapes
    Shape chooseShapeSpecial() {
        Shape[] authorizedShapes = {Shape.ROCK, Shape.PAPER, Shape.SCISSORS, Shape.LIZARD, Shape.SPOCK};
        return authorizedShapes[random.nextInt(authorizedShapes.length)];
    }

}
