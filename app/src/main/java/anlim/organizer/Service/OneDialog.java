package anlim.organizer.Service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;

import anlim.organizer.Enumerator;
import anlim.organizer.R;

public class OneDialog extends AppCompatActivity {

    NumberPicker np;
    String T11, T22, HW2, CW2, T14, T24, HW24, CW24;
    int ResOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_dialog);

        ResOne = Enumerator.ResBut;


        T11 = getIntent().getStringExtra("T11");
        T22 = getIntent().getStringExtra("T22");
        HW2 = getIntent().getStringExtra("HW2");
        CW2 = getIntent().getStringExtra("CW2");

        np = (NumberPicker) findViewById(R.id.numberPicker5);
        np.setMaxValue(9999);
        np.setMinValue(0);

        if (ResOne == 1 ){
            np.setValue(Integer.parseInt(T11));
        }if (ResOne == 2 ){
            np.setValue(Integer.parseInt(T22));
        }if (ResOne == 3 ){
            np.setValue(Integer.parseInt(HW2));
        }if (ResOne == 4 ){
            np.setValue(Integer.parseInt(CW2));
        }
    }

    public void GetResult(View v) {

        if (ResOne == 1 ){

            T14 = String.valueOf(np.getValue());
            Intent intent = new Intent();
            intent.putExtra("T13", T14);
            setResult(1, intent);
            finish();

        }if (ResOne == 2 ){

            T24 = String.valueOf(np.getValue());
            Intent intent = new Intent();
            intent.putExtra("T23", T24);
            setResult(2, intent);
            finish();

        }if (ResOne == 3 ){

            HW24 = String.valueOf(np.getValue());
            Intent intent = new Intent();
            intent.putExtra("HW23", HW24);
            setResult(3, intent);
            finish();

        }if (ResOne == 4 ){

            CW24 = String.valueOf(np.getValue());
            Intent intent = new Intent();
            intent.putExtra("CW23", CW24);
            setResult(4, intent);
            finish();
        }
    }
}
