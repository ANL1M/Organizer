package anlim.organizer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Generator extends AppCompatActivity {

    Random myRandom = new Random();
    int lot1, lot2, lot3, lot4, lot5, lot6;
    TextView tv1, tv2, tv4, tv5, tv6;
    String digit6;

    private static final int SHAKE_SENSITIVITY = 8;

    private SensorManager sensorManager;
    private float accel = SensorManager.GRAVITY_EARTH;
    private float accelPrevious = SensorManager.GRAVITY_EARTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(
                sensorListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        tv1 = (TextView) findViewById(R.id.textView17);
        tv2 = (TextView) findViewById(R.id.textView15);
        tv4 = (TextView) findViewById(R.id.textView18);
        tv5 = (TextView) findViewById(R.id.textView19);
        tv6 = (TextView) findViewById(R.id.textView20);

        GetRandom();
    }

    public void GetRandom(){

        //четыре случайные цифры
        String digit401 = "";
        for (int i = 0; i < 4 ; i++) {
            digit401 += String.valueOf(myRandom.nextInt(9) + " ");}
        tv1.setText(digit401);

        //да или нет
        tv4.setText(String.valueOf(myRandom.nextBoolean()));

        //семь случайных букв
        String char101 = "";
        for (int i = 0; i < 7 ; i++) {
            char101 += String.valueOf((char) (myRandom.nextInt(26) + 'a'));}
        tv5.setText(char101);

        //пароль 10 символов
        String pass101 = "";
        for (int i = 0; i < 10; i++) {
            boolean boolRand = myRandom.nextBoolean();
            if (boolRand == true){
                pass101 += String.valueOf((char) (myRandom.nextInt(26) + 'a'));
            }else{
                pass101 += String.valueOf(myRandom.nextInt(9));
            }}
        tv6.setText(pass101);

        //ArrayList<Integer> lotoRand = new ArrayList<>(6);
        ////lotoRand.ensureCapacity(6);
//
        //for (int i = 0; i < lotoRand.size(); i++) {
        //    int lot = myRandom.nextInt(45) + 1;
        //    boolean checklot = lotoRand.contains(lot);
        //    if (checklot == false){
        //        lotoRand.add(lot);}
        //}
//
        //String lotoRes = "";
        //for (int i = 0; i < lotoRand.size(); i++) {
        //    lotoRes += String.valueOf(lotoRand.get(i));
        //}
        //tv2.setText(lotoRes);
//

        //шесть неповторяющихся цифр
       lot1 = myRandom.nextInt(45) + 1;
       lot2 = myRandom.nextInt(45) + 1;
       lot3 = myRandom.nextInt(45) + 1;
       lot4 = myRandom.nextInt(45) + 1;
       lot5 = myRandom.nextInt(45) + 1;
       lot6 = myRandom.nextInt(45) + 1;

       if (lot1 == 0 || lot2 == lot1 || lot3 == lot1 || lot4 == lot1 || lot5 == lot1 || lot6 == lot1){
           lot1 = myRandom.nextInt(45) + 1;}
       if (lot2 == 0 || lot1 == lot2 || lot3 == lot2 || lot4 == lot2 || lot5 == lot2 || lot6 == lot2){
           lot2 = myRandom.nextInt(45) + 1;}
       if (lot3 == 0 || lot1 == lot3 || lot2 == lot3 || lot4 == lot3 || lot5 == lot3 || lot6 == lot3){
           lot2 = myRandom.nextInt(45) + 1;}
       if (lot4 == 0 || lot1 == lot4 || lot2 == lot4 | lot3 == lot4 || lot5 == lot4 || lot6 == lot4){
           lot2 = myRandom.nextInt(45) + 1;}
       if (lot5 == 0 || lot1 == lot5 || lot2 == lot5 || lot3 == lot5 || lot4 == lot5 || lot6 == lot5){
           lot2 = myRandom.nextInt(45) + 1;}
       if (lot6 == 0 || lot1 == lot6 || lot2 == lot6 || lot3 == lot6 || lot4 == lot6 || lot5 == lot6){
           lot2 = myRandom.nextInt(45) + 1;}
       digit6 = String.valueOf(lot1) + "  " + String.valueOf(lot2)
               + "  " + String.valueOf(lot3) + "  " + String.valueOf(lot4)
               + "  " + String.valueOf(lot5) + "  " + String.valueOf(lot6);
       tv2.setText(digit6);

        }

    public void ClickRandom (View v) {
        GetRandom();
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            accelPrevious = accel;
            accel = (float) Math.sqrt((double) (x * x + y * y + z * z));
            if (accel - accelPrevious > SHAKE_SENSITIVITY) {
                onShake();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    protected void onShake(){
        GetRandom();
    }


}
