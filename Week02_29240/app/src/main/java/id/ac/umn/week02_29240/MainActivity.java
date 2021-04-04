package id.ac.umn.week02_29240;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {
    EditText angka1, angka2;
    TextView hasil;
    Button btnTambah, btnKurang, btnKali, btnBagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        angka1 = (EditText) this.findViewById(R.id.angka1);
        angka2 = (EditText) this.findViewById(R.id.angka2);
        hasil = (TextView) this.findViewById(R.id.hasil);
        btnTambah = (Button) this.findViewById(R.id.btnTambah);
        btnKurang = (Button) this.findViewById(R.id.btnKurang);
        btnKali = (Button) this.findViewById(R.id.btnKali);
        btnBagi = (Button) this.findViewById(R.id.btnBagi);

        btnTambah.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {hitung('+');}
        });
        btnKurang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {hitung('-');}
        });
        btnKali.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {hitung('*');}
        });
        btnBagi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {hitung('/');}
        });


    }
    protected void hitung(char operator){
        double operand1 = (TextUtils.isEmpty(angka1.getText()) ? 0 : Double.parseDouble(angka1.getText().toString()));
        double operand2 = (TextUtils.isEmpty(angka2.getText()) ? 0 : Double.parseDouble(angka2.getText().toString()));
//        double operand2 = Double.parseDouble(angka2.getText().toString());
        double res = 0;
        switch(operator){
            case('+') : res = operand1 + operand2; break;
            case('-') : res = operand1 - operand2; break;
            case('*') : res = operand1 * operand2; break;
            case('/') : res = operand1/operand2;
        }
        if(TextUtils.isEmpty(angka1.getText()) || TextUtils.isEmpty(angka2.getText())){
            hasil.setText("Invalid input!");
        }
        else {
            hasil.setText(String.valueOf(res));
        }

    }
}