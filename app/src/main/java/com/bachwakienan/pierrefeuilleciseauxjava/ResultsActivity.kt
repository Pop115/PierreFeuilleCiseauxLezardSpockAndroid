package com.bachwakienan.pierrefeuilleciseauxjava

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ResultsActivity : AppCompatActivity() {
    private var shape0: Shape? = null
    private var shape1: Shape? = null
    private var hasShape0Won = 0
    private var shapePlayed0: ImageView? = null
    private var shapePlayed1: ImageView? = null
    private var textView0: TextView? = null
    private var textView1: TextView? = null
    private var textViewScore0: TextView? = null
    private var textViewScore1: TextView? = null
    private var score0 = 0
    private var score1 = 0
    private var buttonNextGame: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        shapePlayed0 = findViewById(R.id.shapePlayed0)
        shapePlayed1 = findViewById(R.id.shapePlayed1)
        textView0 = findViewById(R.id.textView0)
        textView1 = findViewById(R.id.textView1)
        textViewScore0 = findViewById(R.id.score0)
        textViewScore1 = findViewById(R.id.score1)
        buttonNextGame = findViewById(R.id.buttonNextGame)
        buttonNextGame?.setOnClickListener { finish() }
        //Récupération des paramètres envoyés
        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                shape0 = Shape.SCISSORS
                shape1 = Shape.SCISSORS
                hasShape0Won = 0
                score0 = 0
                score1 = 0
            } else {
                shape0 = extras.getSerializable("shape0") as Shape?
                shape1 = extras.getSerializable("shape1") as Shape?
                score0 = extras.getInt("score0")
                score1 = extras.getInt("score1")
                hasShape0Won = extras.getInt("hasShape0Won")
            }
        } else {
            shape0 = savedInstanceState.getSerializable("shape0") as Shape?
            shape1 = savedInstanceState.getSerializable("shape1") as Shape?
            hasShape0Won = savedInstanceState.getInt("hasShape0Won")
            score0 = savedInstanceState.getInt("score0")
            score1 = savedInstanceState.getInt("score1")
        }
        shapePlayed0?.setImageDrawable(getDrawableFromShape(shape0))
        shapePlayed1?.setImageDrawable(getDrawableFromShape(shape1))
        when (hasShape0Won) {
            0 -> {
                textView0?.setText(R.string.tie)
                textView0?.setTextColor(ContextCompat.getColor(applicationContext, R.color.tie))
                textView1?.setText(R.string.tie)
                textView1?.setTextColor(ContextCompat.getColor(applicationContext, R.color.tie))
            }
            1 -> {
                textView0?.setText(R.string.victory)
                textView0?.setTextColor(ContextCompat.getColor(applicationContext, R.color.victory))
                textView1?.setText(R.string.defeat)
                textView1?.setTextColor(ContextCompat.getColor(applicationContext, R.color.defeat))
            }
            -1 -> {
                textView1?.setText(R.string.victory)
                textView1?.setTextColor(ContextCompat.getColor(applicationContext, R.color.victory))
                textView0?.setText(R.string.defeat)
                textView0?.setTextColor(ContextCompat.getColor(applicationContext, R.color.defeat))
            }
        }
        var stringScore = resources.getText(R.string.score).toString() + score0
        textViewScore0?.text = stringScore
        stringScore = resources.getText(R.string.score).toString() + score1
        textViewScore1?.text = stringScore
    }

    private fun getDrawableFromShape(shape: Shape?): Drawable? {
        when (shape) {
            Shape.ROCK -> return getDrawable(R.drawable.rock)
            Shape.PAPER -> return getDrawable(R.drawable.paper)
            Shape.SCISSORS -> return getDrawable(R.drawable.scissors)
            Shape.LIZARD -> return getDrawable(R.drawable.lizard)
            Shape.SPOCK -> return getDrawable(R.drawable.spock)
        }
        return null
    }

    public override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onBackPressed() {
        val setIntent = Intent(this, MainActivity::class.java)
        startActivity(setIntent)
    }
}