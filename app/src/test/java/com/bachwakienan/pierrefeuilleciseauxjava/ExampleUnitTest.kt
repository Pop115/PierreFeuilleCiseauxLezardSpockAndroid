package com.bachwakienan.pierrefeuilleciseauxjava

import com.bachwakienan.pierrefeuilleciseauxjava.DuelHelper.hasShape0Won
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    /*
        Teste la sélection des formes des IA, les formes choisies étant aléatoires, je teste 1000 décisions
     */
    @Test
    fun computerChoose() {
        val computerPlayer = ComputerPlayer(1)
        val normalShapes = listOf(Shape.PAPER, Shape.ROCK, Shape.SCISSORS)
        for (i in 0..999) {
            Assert.assertTrue(normalShapes.contains(computerPlayer.chooseShape()))
        }
        val specialShapes = listOf(*Shape.values())
        for (i in 0..999) {
            Assert.assertTrue(specialShapes.contains(computerPlayer.chooseShapeSpecial()))
        }
    }

    /*
    Partie la plus importante du jeu donc je teste toutes les combinaisons
     */
    @Test
    fun winAgainst() {
        Assert.assertEquals(0, hasShape0Won(Shape.LIZARD, Shape.LIZARD).toLong())
        Assert.assertEquals(0, hasShape0Won(Shape.SPOCK, Shape.SPOCK).toLong())
        Assert.assertEquals(0, hasShape0Won(Shape.SCISSORS, Shape.SCISSORS).toLong())
        Assert.assertEquals(0, hasShape0Won(Shape.PAPER, Shape.PAPER).toLong())
        Assert.assertEquals(0, hasShape0Won(Shape.ROCK, Shape.ROCK).toLong())
        Assert.assertEquals(1, hasShape0Won(Shape.ROCK, Shape.SCISSORS).toLong())
        Assert.assertEquals(-1, hasShape0Won(Shape.ROCK, Shape.PAPER).toLong())
        Assert.assertEquals(1, hasShape0Won(Shape.PAPER, Shape.ROCK).toLong())
        Assert.assertEquals(-1, hasShape0Won(Shape.PAPER, Shape.SCISSORS).toLong())
        Assert.assertEquals(1, hasShape0Won(Shape.SCISSORS, Shape.PAPER).toLong())
        Assert.assertEquals(-1, hasShape0Won(Shape.SCISSORS, Shape.ROCK).toLong())
        Assert.assertEquals(1, hasShape0Won(Shape.SCISSORS, Shape.PAPER).toLong())
        Assert.assertEquals(-1, hasShape0Won(Shape.SCISSORS, Shape.ROCK).toLong())
        Assert.assertEquals(1, hasShape0Won(Shape.SPOCK, Shape.SCISSORS).toLong())
        Assert.assertEquals(1, hasShape0Won(Shape.SPOCK, Shape.ROCK).toLong())
        Assert.assertEquals(-1, hasShape0Won(Shape.SPOCK, Shape.PAPER).toLong())
        Assert.assertEquals(-1, hasShape0Won(Shape.SPOCK, Shape.LIZARD).toLong())
        Assert.assertEquals(1, hasShape0Won(Shape.LIZARD, Shape.SPOCK).toLong())
        Assert.assertEquals(1, hasShape0Won(Shape.LIZARD, Shape.PAPER).toLong())
        Assert.assertEquals(-1, hasShape0Won(Shape.LIZARD, Shape.ROCK).toLong())
        Assert.assertEquals(-1, hasShape0Won(Shape.LIZARD, Shape.SCISSORS).toLong())
    }
}