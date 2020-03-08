package com.bachwakienan.pierrefeuilleciseauxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isSpecialMode = false;
    private Button specialModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button computerVsComputerButton = findViewById(R.id.button_ordivsordi);
        Button playerVsComputerButton = findViewById(R.id.button_joueurvsordi);
        specialModeButton = findViewById(R.id.button_specialmode);

        computerVsComputerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startDuel(DuelType.COMPUTER_VS_COMPUTER);
            }
        });

        playerVsComputerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startDuel(DuelType.PLAYER_VS_COMPUTER);
            }
        });

        specialModeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggleSpecialMode();
            }
        });
    }

    public void toggleSpecialMode() {
        isSpecialMode = !isSpecialMode;
        if (isSpecialMode) {
            specialModeButton.setBackgroundColor(getResources().getColor(R.color.special_activated));
            specialModeButton.setText(R.string.special_mode_activated);
        } else {
            specialModeButton.setBackgroundColor(getResources().getColor(R.color.special_disabled));
            specialModeButton.setText(R.string.special_mode_disabled);

        }
    }

    public void startDuel(DuelType duelType) {
        if(duelType == DuelType.PLAYER_VS_COMPUTER) {
            Intent intent = new Intent(this, DuelActivity.class);
            intent.putExtra("isSpecialMode", isSpecialMode);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, DuelActivityComputerVsComputer.class);
            intent.putExtra("isSpecialMode", isSpecialMode);
            startActivity(intent);
        }
    }


}
