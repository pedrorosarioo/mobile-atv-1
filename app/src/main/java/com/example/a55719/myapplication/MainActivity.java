package com.example.a55719.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public static String USERNAME;
    public static String PASSWORD;
    public static int TRIES;
    public Button loginBtn;
    public Button leaveBtn;
    public EditText userInput;
    public EditText passwordInput;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
        this.USERNAME = this.sharedPreferences.getString("username", "pedro");
        this.PASSWORD = this.sharedPreferences.getString("password", "123");
        this.TRIES = this.sharedPreferences.getInt("tries", 0);
//        this.TRIES = 0;


        if (this.TRIES >= 3) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Você foi bloqueado!");
            builder.setMessage("Você errou o login mais de 3x e por isso foi bloqueado");
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface arg0) {
                    finish();
                }
            });
        }

        this.loginBtn = (Button) findViewById(R.id.button3);
        this.leaveBtn = (Button) findViewById(R.id.button4);
        this.userInput = (EditText) findViewById(R.id.editText);
        this.passwordInput = (EditText) findViewById(R.id.editText2);

        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = MainActivity.this.userInput.getText().toString();
                String password = MainActivity.this.passwordInput.getText().toString();
                String USERNAME = MainActivity.USERNAME;
                String PASSWORD = MainActivity.PASSWORD;
                if (user.equals(USERNAME) && password.equals(PASSWORD)){
                    MainActivity.this.editor.putInt("tries", 0).apply();
                    MainActivity.TRIES=0;
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    MainActivity.this.editor.putInt("tries", MainActivity.TRIES+1).apply();
                    MainActivity.TRIES = MainActivity.TRIES+1;
                    if (MainActivity.TRIES >= 3) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Você foi bloqueado!");
                        builder.setMessage("Você errou o login mais de 3x e por isso foi bloqueado");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                            @Override
                            public void onDismiss(DialogInterface arg0) {
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(MainActivity.this, "Usuário ou senha incorretos, você ainda tem "+(3-MainActivity.TRIES)+" tentativas.", Toast.LENGTH_SHORT).show();
                        MainActivity.this.passwordInput.setText("");
                    }

                }
            }
        });

        this.leaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
