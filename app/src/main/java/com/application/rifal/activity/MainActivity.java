package com.application.rifal.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.application.rifal.myapplication.R;
import com.application.rifal.myapplication.fragment.General;
import com.application.rifal.myapplication.fragment.MainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bot = findViewById(R.id.nav);

        MainFragment hf = new MainFragment();
        loadFragment(hf);

        bot.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fr) {
        if (fr != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, fr).commit();
            return true;
        }
        return false;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fr = null;


        switch (item.getItemId()) {
            case R.id.a:
                fr = new MainFragment();
                break;
            case R.id.b:
                fr = new General();
                break;

        }


        return loadFragment(fr);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Apps")
                .setMessage("Are you sure you want to close this NEWS°APPS?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
