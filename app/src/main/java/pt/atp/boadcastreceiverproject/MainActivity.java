package pt.atp.boadcastreceiverproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
        ((Button)findViewById(R.id.btnCall)).setOnClickListener((view)->{
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("tel:963117321"));
            startActivity(intent);
        });
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


}
