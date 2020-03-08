package com.bachwakienan.pierrefeuilleciseauxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    Shape shape0;
    Shape shape1;
    int hasShape0Won;

    ImageView shapePlayed0;
    ImageView shapePlayed1;

    TextView textView0;
    TextView textView1;

    TextView textViewScore0;
    TextView textViewScore1;

    int score0;
    int score1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        shapePlayed0 = findViewById(R.id.shapePlayed0);
        shapePlayed1 = findViewById(R.id.shapePlayed1);
        textView0 = findViewById(R.id.textView0);
        textView1 = findViewById(R.id.textView1);
        textViewScore0 = findViewById(R.id.score0);
        textViewScore1 = findViewById(R.id.score1);

        //Récupération des paramètres envoyés
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                shape0 = Shape.SCISSORS;
                shape1 = Shape.SCISSORS;
                hasShape0Won = 0;
            } else {
                shape0 = (Shape) extras.getSerializable("shape0");
                shape1 = (Shape) extras.getSerializable("shape1");
                hasShape0Won = extras.getInt("hasShape0Won");
            }
        } else {
            shape0 = (Shape) savedInstanceState.getSerializable("shape0");
            shape1 = (Shape) savedInstanceState.getSerializable("shape1");
            hasShape0Won = savedInstanceState.getInt("hasShape0Won");
        }


        shapePlayed0.setImageDrawable(getDrawableFromShape(shape0));
        shapePlayed1.setImageDrawable(getDrawableFromShape(shape1));
        if (hasShape0Won == 0) {
            textView0.setText(R.string.tie);
            textView0.setTextColor(getResources().getColor(R.color.tie));

            textView1.setText(R.string.tie);
            textView1.setTextColor(getResources().getColor(R.color.tie));
        } else if (hasShape0Won == 1) {
            textView0.setText(R.string.victory);
            textView0.setTextColor(getResources().getColor(R.color.victory));

            textView1.setText(R.string.defeat);
            textView1.setTextColor(getResources().getColor(R.color.defeat));
        } else if (hasShape0Won == -1) {
            textView1.setText(R.string.victory);
            textView1.setTextColor(getResources().getColor(R.color.victory));

            textView0.setText(R.string.defeat);
            textView0.setTextColor(getResources().getColor(R.color.defeat));
        }

        String stringScore = String.valueOf(getResources().getText(R.string.score)) + score0;
        textViewScore0.setText(stringScore);
        stringScore = String.valueOf(getResources().getText(R.string.score)) + score1;
        textViewScore1.setText(stringScore);


    }


    Drawable getDrawableFromShape(Shape shape) {
        switch (shape) {
            case ROCK:
                return getDrawable(R.drawable.rock);
            case PAPER:
                return getDrawable(R.drawable.paper);
            case SCISSORS:
                return getDrawable(R.drawable.scissors);
            case LIZARD:
                return getDrawable(R.drawable.lizard);
            case SPOCK:
                return getDrawable(R.drawable.spock);
        }
        return null;
    }

}
