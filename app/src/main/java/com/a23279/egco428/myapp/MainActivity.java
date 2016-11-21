package com.a23279.egco428.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Sin;
    Button Sup;
    Button cancle;
    EditText user;
    EditText pass;

    private LoginDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sin = (Button)findViewById(R.id.signin);
        Sup = (Button)findViewById(R.id.signup);
        cancle = (Button)findViewById(R.id.cancle);

        user = (EditText)findViewById(R.id.userN);
        pass = (EditText)findViewById(R.id.passW);

        dataSource = new LoginDataSource(this);
        dataSource.open();

    }

    public void clickSignup(View view){
        startActivity(new Intent(MainActivity.this, SignUpPage.class));
    }

    public void clickSignin(View view){
        checkPassword();
    }

    public void clickcancle(View view){
        user.setText("");
        pass.setText("");
    }

    private void checkPassword(){

        String name = user.getText().toString();
        String loginMessage = dataSource.findpass(name);

        String passw = pass.getText().toString();

        if (loginMessage.equals("")) {

            Toast.makeText(getApplicationContext(), "Login fail",
                    Toast.LENGTH_SHORT).show();

        }

        else {
            if (loginMessage.equals(passw)) {
                Toast.makeText(getApplicationContext(), "Login success",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MainPage.class));
            }
            else {
                Toast.makeText(getApplicationContext(), "Login fail",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume(){
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        dataSource.close();
        super.onPause();
    }

}
