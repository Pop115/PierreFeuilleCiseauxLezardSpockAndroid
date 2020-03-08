package com.bachwakienan.pierrefeuilleciseauxjava

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DuelActivity : AppCompatActivity() {
    private var selectedShape: Shape? = null
    private var defaultBackground: Drawable? = null
    private var isSpecialMode = false
    private var score0 = 0
    private var score1 = 0
    private var textViewScore0: TextView? = null
    private var textViewScore1: TextView? = null
    private var lizardButton: ImageButton? = null
    private var spockButton: ImageButton? = null
    private var rockButton: ImageButton? = null
    private var scissorsButton: ImageButton? = null
    private var paperButton: ImageButton? = null
    private var playButton: Button? = null
    private var computerPlayer0: ComputerPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duel)
        textViewScore0 = findViewById(R.id.score0)
        textViewScore1 = findViewById(R.id.score1)
        lizardButton = findViewById(R.id.image_lizard)
        spockButton = findViewById(R.id.image_spock)
        rockButton = findViewById(R.id.image_rock)
        scissorsButton = findViewById(R.id.image_scissors)
        paperButton = findViewById(R.id.image_paper)
        playButton = findViewById(R.id.button_play)

        lizardButton?.setOnClickListener { chooseShape(Shape.LIZARD) }
        spockButton?.setOnClickListener { chooseShape(Shape.SPOCK) }
        rockButton?.setOnClickListener { chooseShape(Shape.ROCK) }
        paperButton?.setOnClickListener { chooseShape(Shape.PAPER) }
        scissorsButton?.setOnClickListener { chooseShape(Shape.SCISSORS) }
        playButton?.setOnClickListener { play() }

        //Récupération du background android par défaut de l'ImageButton pour le remettre lorsque le bouton n'est plus sélectionné
        defaultBackground = lizardButton?.background
        //Récupération des paramètres envoyés
        isSpecialMode = if (savedInstanceState == null) {
            val extras = intent.extras
            extras?.getBoolean("isSpecialMode") ?: false
        } else {
            savedInstanceState.getBoolean("isSpecialMode")
        }
        //On affiche ou cache les gestes spéciaux en fonction de la sélection du joueur
        if (isSpecialMode) {
            lizardButton?.visibility = View.VISIBLE
            spockButton?.visibility = View.VISIBLE
        } else {
            lizardButton?.visibility = View.GONE
            spockButton?.visibility = View.GONE
        }
        computerPlayer0 = ComputerPlayer()
        updateResultDisplay()
    }

    private fun chooseShape(shape: Shape) {
        var imageButton: ImageButton?
        if (selectedShape != null) {
            imageButton = getButtonFromShape(selectedShape!!)
            if (imageButton != null) imageButton.background = defaultBackground
        }
        imageButton = getButtonFromShape(shape)
        imageButton?.setBackgroundResource(R.drawable.border_selected)
        selectedShape = shape
    }

    private fun play() {
        val hasShape0Won: Int
        val computerShape: Shape = if (isSpecialMode) computerPlayer0!!.chooseShapeSpecial() else computerPlayer0!!.chooseShape()
        hasShape0Won = DuelHelper.hasShape0Won(selectedShape, computerShape)
        if (hasShape0Won == 1) score0++ else if (hasShape0Won == -1) score1++
        updateResultDisplay()
        showResults(selectedShape, computerShape, hasShape0Won)
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
        intent.putExtra("duelType", DuelType.PLAYER_VS_COMPUTER)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun getButtonFromShape(shape: Shape): ImageButton? {
        return when (shape) {
            Shape.ROCK -> rockButton
            Shape.PAPER -> paperButton
            Shape.SCISSORS -> scissorsButton
            Shape.LIZARD -> lizardButton
            Shape.SPOCK -> spockButton
        }
    }
}