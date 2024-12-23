package com.example.hw1_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText messageInput = findViewById(R.id.message_input);
        Button sendMessageButton = findViewById(R.id.btn_sendMessage);
        Button buttonNext = findViewById(R.id.btn_next);

        sendMessageButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            if (shareIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(shareIntent, "Поделиться через"));
            } else {
                Toast.makeText(this, "Нет приложения для отправки", Toast.LENGTH_SHORT).show();
            }
        });

        buttonNext.setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();
            Intent intent = new Intent(
                    MainActivity.this,
                    SecondActivity.class
            );
            intent.putExtra("message", message);
            startActivity(intent);
        });
    }
}