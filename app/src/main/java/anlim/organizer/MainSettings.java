package anlim.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import anlim.organizer.Service.SQLhelper;

public class MainSettings extends AppCompatActivity {

    public static final String DefSeas = "10";
    public static final String DefEp = "25";
    public static String getSeas, getEpi;
    EditText ETCS, ETEP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);

        ETCS = (EditText) findViewById(R.id.editText6);
        ETEP = (EditText) findViewById(R.id.editText7);

        SQLhelper sqLhelper = new SQLhelper(this);
        List<String> settTvShow;
        settTvShow = sqLhelper.getCSeas();

        if (settTvShow.isEmpty()) {
            ETCS.setText(DefSeas);
            ETEP.setText(DefEp);
        } else {
            ETCS.setText(settTvShow.get(0));
            ETEP.setText(settTvShow.get(1));
        }

    }

    public void clickSettings(View v){

        switch (v.getId()){
            case R.id.button10:

                getSeas = ETCS.getText().toString();
                getEpi = ETEP.getText().toString();

                List<String> showSettings = new ArrayList<String>();
                showSettings.add(getSeas);
                showSettings.add(getEpi);

                SQLhelper sqLhelper2 = new SQLhelper(this);
                sqLhelper2.addData(showSettings);

                Intent intent = new Intent(this, Instruments.class);
                startActivity(intent);
                finish();
                break;

            case R.id.button11:
                Intent intent2 = new Intent(this, Instruments.class);
                startActivity(intent2);
                finish();
                break;
        }

    }

}
