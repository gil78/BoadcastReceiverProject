package pt.atp.boadcastreceiverproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int CALL_TOKEN = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openPhotoActivity();
        openSite();
        makeCall();
        openMap();

    }

    private void openPhotoActivity(){
        ((Button)findViewById(R.id.btnPhoto)).setOnClickListener((view)->{
            Intent intent = new Intent(this,PhotoActivity.class);
            startActivity(intent);
        });
    }

    private void openSite(){
        ((Button)findViewById(R.id.btnSite)).setOnClickListener((view)->{
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("http://www.sapo.pt"));
            startActivity(intent);
        });
    }

    private void makeCall(){
        //2ª opção - permissão para a nossa aplicação fazer chamadas
        ((Button)findViewById(R.id.btnCall)).setOnClickListener((view)->{
            Intent intent = new Intent();
            intent.setAction("android.intent.action.CALL");
            intent.setData(Uri.parse("tel:963117321"));
            if(!permissionGranted())
                return;
            startActivity(intent);
        });
        //1ª opção - a nossa aplicação não tem permissões para usa outra aplicação para fazer a chamada
       /* ((Button)findViewById(R.id.btnCall)).setOnClickListener((view)->{
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("tel:963117321"));
            startActivity(intent);
        });*/
    }

    private void openMap(){
        ((Button)findViewById(R.id.btnMap)).setOnClickListener((view)->{
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
             //Lisboa
            intent.setData(Uri.parse("geo:38.72847475333044, -9.139262710997748"));
            //Lisboa Escolas
            intent.setData(Uri.parse("geo:38.72847475333044, -9.139262710997748?q=escolas"));
            //Aveiro
            intent.setData(Uri.parse("geo:0, 0?q=aveiro + escolas"));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        });
    }

    private boolean permissionGranted(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    CALL_TOKEN); //O requestCode é importante para depois tratarmos as situações em que o utilizador não dá permissões. Basta depois chamar o método onRequestPermissionsResult

            return false;
        }
        return true;
    }



}
