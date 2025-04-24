package com.example.osobiste_powitanie;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText imie_input;
    private Button btn_przywitaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imie_input=findViewById(R.id.imie_input);
        btn_przywitaj=findViewById(R.id.button);

        String imie_input_value=imie_input.getText().toString().trim();

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        if(imie_input_value.isEmpty()){
            builder.setTitle("Błąd");
            builder.setMessage("Proszę wpisać swoje imię!");
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this,"")
                }
            });
        }
    }

}