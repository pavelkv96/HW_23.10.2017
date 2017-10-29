package com.github.pavelkv96.hw_23102017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginMainButton = (Button) findViewById(R.id.login_main_button);
        loginMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MainActivity.this, SecondActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //finish();
                startActivity(mainIntent);
            }
        });
    }
}
