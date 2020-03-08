package com.bachwakienan.pierrefeuilleciseauxjava;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class DuelActivityComputerVsComputer extends AppCompatActivity {

    private boolean isSpecialMode;

    private int score0 = 0;
    private int score1 = 0;

    private TextView textViewScore0;
    private TextView textViewScore1;

    ComputerPlayer computerPlayer0;
    ComputerPlayer computerPlayer1;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duel_computer_vs_computer);

        textViewScore0 = findViewById(R.id.score0);
        textViewScore1 = findViewById(R.id.score1);

        //Récupération des paramètres envoyés
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                isSpecialMode = false;
            } else {
                isSpecialMode = extras.getBoolean("isSpecialMode");
            }
        } else {
            isSpecialMode = savedInstanceState.getBoolean("isSpecialMode");
        }

        computerPlayer0 = new ComputerPlayer();
        computerPlayer1 = new ComputerPlayer();
    }

    @Override
    public void onResume(){
        super.onResume();
        updateResultDisplay();

        handler = new Handler();

        final Runnable runnable = new Runnable() {
            public void run() {
                play();
            }
        };

        //Attend 1 seconde pour que le joueur croit que les ordinateurs réflechissent, sans ce temps la transition est trop rapide et peu intéressante pour un joueur
        handler.postDelayed(runnable, 1000);

    }

    void play() {
        int hasShape0Won;

        Shape computer0Shape;
        Shape computer1Shape;
        if (isSpecialMode) {
            computer0Shape = computerPlayer0.chooseShapeSpecial();
            computer1Shape = computerPlayer1.chooseShapeSpecial();
        } else {
            computer0Shape = computerPlayer0.chooseShape();
            computer1Shape = computerPlayer1.chooseShape();
        }
        hasShape0Won = DuelHelper.hasShape0Won(computer0Shape, computer1Shape);

        if (hasShape0Won == 1)
            score0++;
        else if (hasShape0Won == -1)
            score1++;

        updateResultDisplay();

        showResults(computer0Shape, computer1Shape, hasShape0Won);


    }

    void updateResultDisplay() {
        String stringScore = String.valueOf(getResources().getText(R.string.score)) + score0;
        textViewScore0.setText(stringScore);
        stringScore = String.valueOf(getResources().getText(R.string.score)) + score1;
        textViewScore1.setText(stringScore);
    }

    void showResults(Shape shape0, Shape shape1, int hasShape0Won) {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("shape0", shape0);
        intent.putExtra("shape1", shape1);
        intent.putExtra("hasShape0Won", hasShape0Won);
        intent.putExtra("score0", score0);
        intent.putExtra("score1", score1);
        intent.putExtra("duelType", DuelType.COMPUTER_VS_COMPUTER);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
    }

}
