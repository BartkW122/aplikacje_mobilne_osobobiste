package com.example.osobiste_powitanie;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static  final  String CH_ID="ID";

    private EditText imie_input;
    private Button btn_przywitaj;
    private String imie_input_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imie_input=findViewById(R.id.imie_input);
        btn_przywitaj=findViewById(R.id.button);



        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        AlertDialog.Builder builder2=new AlertDialog.Builder(this);
        CreatNotifiChannel();
        btn_przywitaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imie_input_value=imie_input.getText().toString().trim();

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
                            Powiadomienie();
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
    private  void  CreatNotifiChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CH_ID, "kanal_powiadomien!", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("opis_kanalu");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private  void Powiadomienie(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CH_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Witaj!").setStyle(new NotificationCompat.BigTextStyle().bigText("Miło cię widzieć , " + imie_input_value + " !")).setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
            NotificationManagerCompat notificationM = NotificationManagerCompat.from(this);
            notificationM.notify(1, builder.build());
        }
    }
}