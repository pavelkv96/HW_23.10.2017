package com.github.pavelkv96.hw_23102017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button mClickHereButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mClickHereButton = (Button) findViewById(R.id.click_here_button);
        mClickHereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
