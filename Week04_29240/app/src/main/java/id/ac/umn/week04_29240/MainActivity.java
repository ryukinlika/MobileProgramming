package id.ac.umn.week04_29240;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText text, url;
    private Button bSend, bBrowse, bFragment;
    private TextView ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.isian);
        url = findViewById(R.id.url);
        bSend = findViewById(R.id.buttonKirim);
        bBrowse = findViewById(R.id.buttonBrowse);
        bFragment = findViewById(R.id.btnFragment);
        ans = findViewById(R.id.answer);

        bBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dest = url.getText().toString();
                if(dest.isEmpty()){
                    dest = "https://www.google.com";
                }
                Intent browseIntent = new Intent(Intent.ACTION_VIEW);
                browseIntent.setData(Uri.parse(dest));
                Log.d("d", dest);
                if(browseIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(browseIntent);
                }
            }
        });

        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(MainActivity.this, SecondActivity.class);
                String input = text.getText().toString();
                sendIntent.putExtra("messageMain", input);
                startActivityForResult(sendIntent, 1);
            }
        });

        bFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fragIntent = new Intent(MainActivity.this, FragActivity.class);
                startActivity(fragIntent);
            }
        }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String answer = data.getStringExtra("answer");
                ans.setText(answer);
            }
        }
    }


}