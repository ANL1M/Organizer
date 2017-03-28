package anlim.organizer;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Flashlight extends AppCompatActivity {

    public boolean hasFlash;
    public static boolean FlashSt;
    ImageButton Switch;

    Camera cam = Camera.open();
    Camera.Parameters p = cam.getParameters();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);

        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!hasFlash) {
            Toast.makeText(this, "Нет фонарика", Toast.LENGTH_LONG).show();
        }

        Switch = (ImageButton) findViewById(R.id.imageButton);
        StartFlash();
    }

    @Override
    public void onBackPressed() {

        OffFlash();
        super.onBackPressed();
        finish();
    }

    public void OnFlash(View v){

        if (FlashSt == true){
            StopFlash();
        }else {
            StartFlash();
        }
    }

    public void StartFlash(){

        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();
        FlashSt = true;
        Switch.setImageResource(R.mipmap.ic_sun_ton1);
    }

    public void StopFlash(){

        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        cam.setParameters(p);
        cam.startPreview();
        FlashSt = false;
        Switch.setImageResource(R.mipmap.ic_sun_on);
    }

    public void OffFlash(){

        cam.stopPreview();
        cam.release();
        FlashSt = false;
        Switch.setImageResource(R.mipmap.ic_sun_on);
    }
}
