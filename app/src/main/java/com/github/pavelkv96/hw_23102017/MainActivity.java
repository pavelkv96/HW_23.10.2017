package com.github.pavelkv96.hw_23102017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    HashMap<String, String> hashMapValueEditTexts;
    private EditText mEmailMainEditText;
    private EditText mPasswordMainEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailMainEditText = (EditText) findViewById(R.id.email_main_edit_text);
        mPasswordMainEditText = (EditText) findViewById(R.id.password_main_edit_text);

        hashMapValueEditTexts = new HashMap<>();

        Button loginMainButton = (Button) findViewById(R.id.login_main_button);

        loginMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hashMapValueEditTexts.put("email", mEmailMainEditText.getText().toString());
                hashMapValueEditTexts.put("password", mPasswordMainEditText.getText().toString());
                Intent mainIntent = new Intent(MainActivity.this, SecondActivity.class);
                mainIntent.putExtra("hash_map_value_edit_texts", hashMapValueEditTexts);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //finish();
                startActivity(mainIntent);
            }
        });
    }
}
