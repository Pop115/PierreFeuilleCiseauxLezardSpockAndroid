package com.bachwakienan.pierrefeuilleciseauxjava

import java.util.*

class ComputerPlayer {
    private var random: Random

    internal constructor() {
        random = Random()
    }

    internal constructor(seed: Long) {
        random = Random(seed)
    }

    /*
        This one is a totally random bot but we could imagine a bot with predefined moves
        so the player could learn how to win against a particular bot and
        then he could fight against other different bots in different levels.
     */
    fun chooseShape(): Shape {
        val authorizedShapes = arrayOf(Shape.ROCK, Shape.PAPER, Shape.SCISSORS)
        return authorizedShapes[random.nextInt(authorizedShapes.size)]
    }

    //chooseShape but with special shapes
    fun chooseShapeSpecial(): Shape {
        val authorizedShapes = arrayOf(Shape.ROCK, Shape.PAPER, Shape.SCISSORS, Shape.LIZARD, Shape.SPOCK)
        return authorizedShapes[random.nextInt(authorizedShapes.size)]
    }
}