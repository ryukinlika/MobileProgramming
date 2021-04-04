package id.ac.umn.week04_29240;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView tvReceivedText;
    private EditText etReply;
    private Button btnReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvReceivedText = findViewById(R.id.receivedText);
        etReply = findViewById(R.id.replyText);
        btnReply = findViewById(R.id.buttonReply);
        Intent mainIntent = getIntent();
        String receivedText = mainIntent.getStringExtra("messageMain");
        tvReceivedText.setText(receivedText);
    }

    public void replyFunction(View view) {
        String answer = etReply.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra("answer", answer);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}