package id.ac.umn.week09_29240;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private EditText etNim, etNama, etEmail, etNomorHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etNomorHP = findViewById(R.id.etPhone);
        Intent intent = getIntent();
        if(intent.hasExtra("MAHASISWA")) {
            Mahasiswa mhs = (Mahasiswa)
                    intent.getSerializableExtra("MAHASISWA");
            etNim.setText(mhs.getNim());
            etNama.setText(mhs.getName());
            etEmail.setText(mhs.getEmail());
            etNomorHP.setText(mhs.getPhone());
            etNim.setEnabled(false);
        } else {
            etNim.setEnabled(true);
        }
    }
    public void saveData(View view){
        String mNIM = etNim.getText().toString();
        String mNama = etNama.getText().toString();
        String mEmail = etEmail.getText().toString();
        String mNoHp = etNomorHP.getText().toString();
        if(mNIM.length() <= 0 || mNama.length() <= 0 ||
                mEmail.length() <= 0 || mNoHp.length() <= 0){
            Toast.makeText(this,"Every field is required",
                    Toast.LENGTH_LONG).show();
        } else {
            Intent intentJawab = new Intent();
            Mahasiswa mhs = new Mahasiswa(mNIM, mNama, mEmail, mNoHp);
            intentJawab.putExtra("MAHASISWA",mhs);
            setResult(RESULT_OK,intentJawab);
            finish();
        }
    }
    public void cancel(View view){
        Intent intentJawab = new Intent();
        setResult(RESULT_CANCELED,intentJawab);
        finish();
    }
}