package com.a23279.egco428.myapp;

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

        Button Btn = (Button)findViewById(R.id.button);

    }

    public void clickBtn(View view){
        startActivity(new Intent(MainActivity.this, SignUpPage.class));
    }

    public void clickBtnMain(View view){
        startActivity(new Intent(MainActivity.this, MainPage.class));
    }

}
