package com.example.a55719.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText confirm;
    private Button submitBtn;
    private Button cancelBtn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        this.username = (EditText) findViewById(R.id.editText3);
        this.password = (EditText) findViewById(R.id.editText4);
        this.confirm = (EditText) findViewById(R.id.editText5);
        this.submitBtn = (Button) findViewById(R.id.button6);
        this.cancelBtn = (Button) findViewById(R.id.button7);
        this.sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();

        this.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ConfigActivity.this.username.getText().toString();
                String password = ConfigActivity.this.password.getText().toString();
                String confirm = ConfigActivity.this.confirm.getText().toString();
                Boolean changed = false;

                if (username.length() > 0) {
                    ConfigActivity.this.editor.putString("username", username).apply();
                    changed = true;
                }

                if (password.length() > 0) {
                    if (password.equals(confirm)){
                        ConfigActivity.this.editor.putString("password", password).apply();
                        changed = true;
                    } else {
                        Toast.makeText(ConfigActivity.this, "Senha e confirmação diferentes", Toast.LENGTH_SHORT).show();
                        changed=false;
                    }

                }

                if (changed) {
                    ConfigActivity.this.username.setText("");
                    ConfigActivity.this.password.setText("");
                    ConfigActivity.this.confirm.setText("");
                    Toast.makeText(ConfigActivity.this, "Dados alterados com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        this.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigActivity.this.username.setText("");
                ConfigActivity.this.password.setText("");
                ConfigActivity.this.confirm.setText("");
            }
        });
    }
}
