package com.example.osobiste_powitanie;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        AlertDialog.Builder builder2=new AlertDialog.Builder(this);

        btn_przywitaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imie_input_value=imie_input.getText().toString().trim();

                if(imie_input_value.isEmpty()){

                    builder.setTitle("Błąd");
                    builder.setMessage("Proszę wpisać swoje imię!");
                    builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this,"OK!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }else{

                    builder2.setTitle("Potwierdzenie");
                    builder2.setMessage("Cześć "+imie_input_value+" ! Czy chcesz otrzymać powiadomienia powitalne?");
                    builder2.setPositiveButton("Tak, poproszę",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            NotificationChannel channel=new NotificationChannel("kanal_id","Witaj!", NotificationManager.IMPORTANCE_DEFAULT);
                            channel.setDescription("Miło cię widzieć , "+imie_input_value+" !");
                            Toast.makeText(MainActivity.this,"Powiadomieni zostało wysłane!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder2.setNegativeButton("Nie, dziękuję",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Toast.makeText(MainActivity.this,"Rozumiemi.Nie wysyłam Powiadomienia!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder2.show();
                }
            }
        });

    }

}