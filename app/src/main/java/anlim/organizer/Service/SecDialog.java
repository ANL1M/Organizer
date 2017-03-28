package anlim.organizer.Service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import anlim.organizer.R;
import anlim.organizer.Serializer;

public class SecDialog extends AppCompatActivity {

    EditText seralName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_dialog);

        seralName = (EditText) findViewById(R.id.editText5);
    }

    public void safeClick(View v){


        switch(v.getId()){

            case R.id.button5:
                String SNa = seralName.getText().toString();

                if (SNa.isEmpty()) {
                }else {

                    MySerial mySerial = new MySerial();
                    mySerial.SetSerName(seralName.getText().toString());
                    mySerial.SetSerSeas("1");
                    mySerial.SetSerEpidode("1");

                    SQLhelper AddInfo = new SQLhelper(SecDialog.this);
                    AddInfo.addSerInfo(mySerial);

                    Intent intent = new Intent(SecDialog.this, Serializer.class);
                    startActivity(intent);
                    finish();}
                break;

            case R.id.button:
                finish();
                break;
        }


    }


}
