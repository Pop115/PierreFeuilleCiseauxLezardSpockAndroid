package com.bachwakienan.pierrefeuilleciseauxjava;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class DuelActivity extends AppCompatActivity {

    private Shape selectedShape;

    private Drawable defaultBackground;

    private boolean isSpecialMode;

    private int score0 = 0;
    private int score1 = 0;

    private TextView textViewScore0;
    private TextView textViewScore1;

    ImageButton lizardButton;
    ImageButton spockButton;
    ImageButton rockButton;
    ImageButton scissorsButton;
    ImageButton paperButton;

    Button playButton;

    ComputerPlayer computerPlayer0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duel);

        textViewScore0 = findViewById(R.id.score0);
        textViewScore1 = findViewById(R.id.score1);

        lizardButton = findViewById(R.id.image_lizard);
        spockButton = findViewById(R.id.image_spock);
        rockButton = findViewById(R.id.image_rock);
        scissorsButton = findViewById(R.id.image_scissors);
        paperButton = findViewById(R.id.image_paper);
        playButton = findViewById(R.id.button_play);

        lizardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseShape(Shape.LIZARD);
            }
        });

        spockButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseShape(Shape.SPOCK);
            }
        });

        rockButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseShape(Shape.ROCK);
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseShape(Shape.PAPER);
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chooseShape(Shape.SCISSORS);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                play();
            }
        });

        //Récupération du background android par défaut de l'ImageButton pour le remettre lorsque le bouton n'est plus sélectionné
        defaultBackground = lizardButton.getBackground();

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

        //On affiche ou cache les gestes spéciaux en fonction de la sélection du joueur
        if (isSpecialMode) {
            lizardButton.setVisibility(View.VISIBLE);
            spockButton.setVisibility(View.VISIBLE);
        } else {
            lizardButton.setVisibility(View.GONE);
            spockButton.setVisibility(View.GONE);
        }

        computerPlayer0 = new ComputerPlayer();
        updateResultDisplay();
    }


    void chooseShape(Shape shape) {
        ImageButton imageButton;

        if (selectedShape != null) {
            imageButton = getButtonFromShape(selectedShape);
            if (imageButton != null)
                imageButton.setBackground(defaultBackground);
        }

        imageButton = getButtonFromShape(shape);
        if (imageButton != null)
            imageButton.setBackgroundResource(R.drawable.border_selected);

        selectedShape = shape;
    }

    void play() {

        int hasShape0Won;
            Shape computerShape;
            if (isSpecialMode)
                computerShape = computerPlayer0.chooseShapeSpecial();
            else
                computerShape = computerPlayer0.chooseShape();

            hasShape0Won = DuelHelper.hasShape0Won(selectedShape, computerShape);

            if(hasShape0Won == 1)
                score0++;
            else if(hasShape0Won == -1)
                score1++;

            updateResultDisplay();

            showResults(selectedShape, computerShape, hasShape0Won);

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
        intent.putExtra("duelType", DuelType.PLAYER_VS_COMPUTER);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
    }

    private ImageButton getButtonFromShape(Shape shape) {
        switch (shape) {
            case ROCK:
                return rockButton;
            case PAPER:
                return paperButton;
            case SCISSORS:
                return scissorsButton;
            case LIZARD:
                return lizardButton;
            case SPOCK:
                return spockButton;
        }
        return null;
    }

}
