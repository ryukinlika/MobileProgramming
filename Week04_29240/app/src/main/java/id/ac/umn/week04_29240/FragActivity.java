package id.ac.umn.week04_29240;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FragActivity extends AppCompatActivity {
    private EditText etName;
    private Button btnPage1, btnPage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        etName = findViewById(R.id.textName);
        btnPage1 = findViewById(R.id.btnFirst);
        btnPage2 = findViewById(R.id.btnSecond);

//        btnPage1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FragActivity.this, FragSecondActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnPage2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FragActivity.this, FragThirdActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void btnPage1Function(View view){
        Intent intent = new Intent(FragActivity.this, FragSecondActivity.class);
        startActivity(intent);
    }
    public void btnPage2Function(View view){
        Intent intent = new Intent(FragActivity.this, FragThirdActivity.class);
        startActivity(intent);
    }
}