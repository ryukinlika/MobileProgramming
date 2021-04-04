package id.ac.umn.week04_29240;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class FragThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_third);

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();

        Fragment firstFragment = new FirstFragment();
        fragTransaction.replace(R.id.third_activity_fragment1, firstFragment);

        Fragment secondFragment = new SecondFragment();
        fragTransaction.replace(R.id.third_activity_fragment2, secondFragment);
        fragTransaction.commit();

    }
}