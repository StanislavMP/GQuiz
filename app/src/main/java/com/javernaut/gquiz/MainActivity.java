package com.javernaut.gquiz;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends LoggingActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragmentContainer) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, QuestionsFragment.makeInstance())
                    .commit();
        }
    }

}
