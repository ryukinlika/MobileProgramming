package id.ac.umn.week06_29240;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button tutorialA, tutorialB, tutorialC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tutorialA = findViewById(R.id.tutorialA);
        tutorialB = findViewById(R.id.tutorialB);
        tutorialC = findViewById(R.id.tutorialC);


        tutorialA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentA = new Intent(MainActivity.this, TutorialA.class);
                startActivity(intentA);
            }
        });
        tutorialB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentB = new Intent(MainActivity.this, TutorialB.class);
                startActivity(intentB);
            }
        });
        tutorialC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentC = new Intent(MainActivity.this, TutorialC.class);
                startActivity(intentC);
            }
        });
    }



}