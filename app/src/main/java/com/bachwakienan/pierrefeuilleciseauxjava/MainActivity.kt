package com.bachwakienan.pierrefeuilleciseauxjava

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private var isSpecialMode = false
    private var specialModeButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val computerVsComputerButton = findViewById<Button>(R.id.button_ordivsordi)
        val playerVsComputerButton = findViewById<Button>(R.id.button_joueurvsordi)
        specialModeButton = findViewById(R.id.button_specialmode)
        computerVsComputerButton.setOnClickListener { startDuel(DuelType.COMPUTER_VS_COMPUTER) }
        playerVsComputerButton.setOnClickListener { startDuel(DuelType.PLAYER_VS_COMPUTER) }
        specialModeButton?.setOnClickListener { toggleSpecialMode() }
    }

    private fun toggleSpecialMode() {
        isSpecialMode = !isSpecialMode
        if (isSpecialMode) {
            specialModeButton!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.special_activated))
            specialModeButton!!.setText(R.string.special_mode_activated)
        } else {
            specialModeButton!!.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.special_disabled))
            specialModeButton!!.setText(R.string.special_mode_disabled)
        }
    }

    private fun startDuel(duelType: DuelType) {
        if (duelType === DuelType.PLAYER_VS_COMPUTER) {
            val intent = Intent(this, DuelActivity::class.java)
            intent.putExtra("isSpecialMode", isSpecialMode)
            startActivity(intent)
        } else {
            val intent = Intent(this, DuelActivityComputerVsComputer::class.java)
            intent.putExtra("isSpecialMode", isSpecialMode)
            startActivity(intent)
        }
    }
}