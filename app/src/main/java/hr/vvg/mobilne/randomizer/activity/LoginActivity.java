package hr.vvg.mobilne.randomizer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hr.vvg.mobilne.randomizer.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupView();
    }

    private void setupView() {
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(e -> login());
    }

    private void login() {

        if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")) {
            Toast.makeText(LoginActivity.this, "Uspješna prijava", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, DogsActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Pogrešno korisničko ime ili lozinka", Toast.LENGTH_SHORT).show();
        }
    }
}
