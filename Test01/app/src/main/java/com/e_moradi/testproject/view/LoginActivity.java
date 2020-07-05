package com.e_moradi.testproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e_moradi.testproject.R;

public class LoginActivity extends AppCompatActivity {
    TextView txtHead, txtHeadDesc;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtHead = findViewById(R.id.txtHead);
        txtHeadDesc = findViewById(R.id.txtHeadDesc);
        btnLogin = findViewById(R.id.btnLogin);
        //set font TextView
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Shift Type Basic.ttf");
        txtHead.setTypeface(typeface);
        txtHeadDesc.setTypeface(typeface);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
