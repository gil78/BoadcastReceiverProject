package pt.atp.boadcastreceiverproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.security.Permission;

public class PhotoActivity extends AppCompatActivity {

    private static final int IMAGE_CAPTURE = 100;
    private static final int CAMERA_TOKEN = 100;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ((Button)findViewById(R.id.btnTakePhoto)).setOnClickListener((view)->{
            openCamera();
        });
    }

    private void openCamera(){

        //Necessário pedir permissão
        if(!permissionGranted())
            return;

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode== IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bitmap imageBitmap = (Bitmap)data.getExtras().get("data");
            ((ImageView)findViewById(R.id.imgPhoto)).setImageBitmap(imageBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private boolean permissionGranted(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    CAMERA_TOKEN); //O requestCode é importante para depois tratarmos as situações em que o utilizador não dá permissões. Basta depois chamar o método onRequestPermissionsResult

            return false;
        }
        return true;
    }
}
