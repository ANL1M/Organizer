package anlim.organizer.Service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.List;

import anlim.organizer.MainSettings;
import anlim.organizer.R;
import anlim.organizer.Serializer;

public class TrinDialog extends AppCompatActivity {

    NumberPicker npSe, npEp;
    TextView tvSeNa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trin_dialog);

        npSe = (NumberPicker) findViewById(R.id.numberPicker6);
        npEp = (NumberPicker) findViewById(R.id.numberPicker7);
        tvSeNa = (TextView) findViewById(R.id.textView10);

        SQLhelper sqLhelper = new SQLhelper(this);
        List<String> settTvShow;
        settTvShow = sqLhelper.getCSeas();

        if (settTvShow.isEmpty()) {
            npSe.setMaxValue(Integer.parseInt(MainSettings.DefSeas));
            npEp.setMaxValue(Integer.parseInt(MainSettings.DefEp));
        } else {
            npSe.setMaxValue(Integer.parseInt(settTvShow.get(0)));
            npEp.setMaxValue(Integer.parseInt(settTvShow.get(1)));
        }

        npSe.setMinValue(1);
        npEp.setMinValue(1);

        String SerNameIR = getIntent().getStringExtra("SerNameI");
        String SerSeasIR = getIntent().getStringExtra("SerSeasI");
        String SerEpiIR = getIntent().getStringExtra("SerEpiI");

        tvSeNa.setText(SerNameIR);
        npSe.setValue(Integer.parseInt(SerSeasIR));
        npEp.setValue(Integer.parseInt(SerEpiIR));
    }

    public void SaveChange (View v){

        int SerIDIR = Integer.parseInt(getIntent().getStringExtra("SerID"));

        MySerial mySerial = new MySerial();
        mySerial.SetSerName(tvSeNa.getText().toString());
        mySerial.SetSerSeas(String.valueOf(npSe.getValue()));
        mySerial.SetSerEpidode(String.valueOf(npEp.getValue()));
        mySerial.SetID(SerIDIR);

        SQLhelper AddInfo = new SQLhelper(TrinDialog.this);

        switch(v.getId()){

            case R.id.button7:
                AddInfo.updateSerData(mySerial);
                break;

            case R.id.imageButton4:
                AddInfo.deleteSerData(mySerial);
                break;

            case R.id.button8:
                break;
        }

        Intent intent = new Intent(TrinDialog.this, Serializer.class);
        startActivity(intent);
        finish();
    }
}
