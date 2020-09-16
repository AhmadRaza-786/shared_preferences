package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private Button saveButton;
    private TextInputEditText editName;
    private TextView resultTextView;
   private static final String ARCHIVE_PREFERENCES = "ArchivePreferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton = findViewById(R.id.buttonSave);
        editName = findViewById(R.id.edtName);
        resultTextView = findViewById(R.id.resultText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(ARCHIVE_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if (editName.toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill the name", Toast.LENGTH_LONG).show();
                } else {
                    String name = editName.getText().toString();
                    editor.putString("name", name);
                    editor.commit();
                    resultTextView.setText("Hello " + name);
                }
            }
        });

        SharedPreferences preferences = getSharedPreferences(ARCHIVE_PREFERENCES, 0);

        if (preferences.contains("name")) {
            String name = preferences.getString("name", "user not defined");
            resultTextView.setText("Hello " + name);
        } else  {
            resultTextView.setText("Hello user not defined");
        }
    }
}