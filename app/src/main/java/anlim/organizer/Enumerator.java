package anlim.organizer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import anlim.organizer.Service.OneDialog;

public class Enumerator extends AppCompatActivity {

    public boolean hasFlash2;
    public static boolean FlashSt2;
    ImageButton Switch;
    EditText etOne, etTwo, etThree, etFour;
    public static String T1, T2, HW, CW;
    public static  int ResBut;
    SharedPreferences SPerF;

    Camera cam2 = Camera.open();
    Camera.Parameters p2 = cam2.getParameters();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enumerator);

        etOne = (EditText) findViewById(R.id.editText);
        etTwo = (EditText) findViewById(R.id.editText2);
        etThree = (EditText) findViewById(R.id.editText3);
        etFour = (EditText) findViewById(R.id.editText4);

        SPerF = getPreferences(MODE_PRIVATE);
        String T1 = SPerF.getString("T1","");
        String T2 = SPerF.getString("T2","");
        String HW = SPerF.getString("HW","");
        String CW = SPerF.getString("CW","");

        if (T1.isEmpty()){
            T1 = "0";
        }

        if (T2.isEmpty()){
            T2 = "0";
        }

        if (HW.isEmpty()){
            HW = "0";
        }

        if (CW.isEmpty()){
            CW = "0";
        }

        etOne.setText(T1);
        etTwo.setText(T2);
        etThree.setText(HW);
        etFour.setText(CW);

        Switch = (ImageButton) findViewById(R.id.imageButton2);
    }

    @Override
    public void onBackPressed() {
        OffFlash();
        super.onBackPressed();
        finish();
    }

    public void OnFlash(View v){

        hasFlash2 = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!hasFlash2) {
            Toast.makeText(this, "Нет фонарика", Toast.LENGTH_LONG).show();
        }

        if (FlashSt2 == true){
            StopFlash();
        }else {
            StartFlash();
        }
    }

    public void StartFlash(){

        p2.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam2.setParameters(p2);
        cam2.startPreview();
        FlashSt2 = true;
        Switch.setImageResource(R.mipmap.ic_sun_ton1);
    }

    public void StopFlash(){

        p2.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        cam2.setParameters(p2);
        cam2.startPreview();
        FlashSt2 = false;
        Switch.setImageResource(R.mipmap.ic_sun_on);
    }

    public void OffFlash(){

        cam2.stopPreview();
        cam2.release();
        FlashSt2 = false;
        Switch.setImageResource(R.mipmap.ic_sun_on);
    }

    public void opDialog(View v){

        switch (v.getId()){
            case R.id.editText:

                Intent intent = new Intent(this, OneDialog.class);
                intent.putExtra("T11", etOne.getText().toString());
                startActivityForResult(intent, 1);
                ResBut = 1;
                break;
        }

        switch (v.getId()){
            case R.id.editText2:

                Intent intent = new Intent(this, OneDialog.class);
                intent.putExtra("T22", etTwo.getText().toString());
                startActivityForResult(intent, 2);
                ResBut = 2;
                break;
        }

        switch (v.getId()){
            case R.id.editText3:

                Intent intent = new Intent(this, OneDialog.class);
                intent.putExtra("HW2", etThree.getText().toString());
                startActivityForResult(intent, 3);
                ResBut = 3;
                break;
        }

        switch (v.getId()){
            case R.id.editText4:

                Intent intent = new Intent(this, OneDialog.class);
                intent.putExtra("CW2", etFour.getText().toString());
                startActivityForResult(intent, 4);
                ResBut = 4;
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (data == null) {return;}

            if (resultCode == 1){
                String T14 = data.getStringExtra("T13");
                etOne.setText(T14);

                SPerF = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed = SPerF.edit();
                ed.putString("T1", T14);
                ed.commit();
            }

            if (resultCode == 2){
                String T24 = data.getStringExtra("T23");
                etTwo.setText(T24);

                SPerF = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed = SPerF.edit();
                ed.putString("T2", T24);
                ed.commit();
            }

            if (resultCode == 3){
                String HW24 = data.getStringExtra("HW23");
                etThree.setText(HW24);

                SPerF = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed = SPerF.edit();
                ed.putString("HW", HW24);
                ed.commit();
            }

            if (resultCode == 4){
                String CW24 = data.getStringExtra("CW23");
                etFour.setText(CW24);

                SPerF = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor ed = SPerF.edit();
                ed.putString("CW", CW24);
                ed.commit();
            }
        }
}
