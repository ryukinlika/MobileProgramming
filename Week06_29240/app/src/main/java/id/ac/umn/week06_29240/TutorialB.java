package id.ac.umn.week06_29240;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TutorialB extends AppCompatActivity {
    AnimationDrawable animasiKuda;
    ImageView gambarKuda;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_b);

        gambarKuda = (ImageView) findViewById(R.id.gambarKuda);
        gambarKuda.setBackgroundResource(R.drawable.kuda_lari);
        animasiKuda = (AnimationDrawable) gambarKuda.getBackground();
        gambarKuda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                animasiKuda.start();
            }
        });

    }
}
