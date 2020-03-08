package com.bachwakienan.pierrefeuilleciseauxjava

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DuelActivityComputerVsComputer : AppCompatActivity() {
    private var isSpecialMode = false
    private var score0 = 0
    private var score1 = 0
    private var textViewScore0: TextView? = null
    private var textViewScore1: TextView? = null
    private var computerPlayer0: ComputerPlayer? = null
    private var computerPlayer1: ComputerPlayer? = null
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duel_computer_vs_computer)
        textViewScore0 = findViewById(R.id.score0)
        textViewScore1 = findViewById(R.id.score1)
        //Récupération des paramètres envoyés
        isSpecialMode = if (savedInstanceState == null) {
            val extras = intent.extras
            extras?.getBoolean("isSpecialMode") ?: false
        } else {
            savedInstanceState.getBoolean("isSpecialMode")
        }
        computerPlayer0 = ComputerPlayer()
        computerPlayer1 = ComputerPlayer()
    }

    public override fun onResume() {
        super.onResume()
        updateResultDisplay()
        handler = Handler()
        val runnable = Runnable { play() }
        //Attend 1 seconde pour que le joueur croit que les ordinateurs réflechissent, sans ce temps la transition est trop rapide et peu intéressante pour un joueur
        handler!!.postDelayed(runnable, 1000)
    }

    private fun play() {
        val hasShape0Won: Int
        val computer0Shape: Shape
        val computer1Shape: Shape
        if (isSpecialMode) {
            computer0Shape = computerPlayer0!!.chooseShapeSpecial()
            computer1Shape = computerPlayer1!!.chooseShapeSpecial()
        } else {
            computer0Shape = computerPlayer0!!.chooseShape()
            computer1Shape = computerPlayer1!!.chooseShape()
        }
        hasShape0Won = DuelHelper.hasShape0Won(computer0Shape, computer1Shape)
        if (hasShape0Won == 1) score0++ else if (hasShape0Won == -1) score1++
        updateResultDisplay()
        showResults(computer0Shape, computer1Shape, hasShape0Won)
    }

    private fun updateResultDisplay() {
        var stringScore = resources.getText(R.string.score).toString() + score0
        textViewScore0!!.text = stringScore
        stringScore = resources.getText(R.string.score).toString() + score1
        textViewScore1!!.text = stringScore
    }

    private fun showResults(shape0: Shape?, shape1: Shape?, hasShape0Won: Int) {
        val intent = Intent(this, ResultsActivity::class.java)
        intent.putExtra("shape0", shape0)
        intent.putExtra("shape1", shape1)
        intent.putExtra("hasShape0Won", hasShape0Won)
        intent.putExtra("score0", score0)
        intent.putExtra("score1", score1)
        intent.putExtra("duelType", DuelType.COMPUTER_VS_COMPUTER)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}