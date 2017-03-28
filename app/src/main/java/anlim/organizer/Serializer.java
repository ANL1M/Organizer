package anlim.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import anlim.organizer.Service.MySerial;
import anlim.organizer.Service.SQLhelper;
import anlim.organizer.Service.SecDialog;
import anlim.organizer.Service.TrinDialog;

public class Serializer extends AppCompatActivity {

    TextView myTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myTv = (TextView) findViewById(R.id.textView9);

        //Нажатие на плавающую кнопку и добавление нового сериала
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Serializer.this, SecDialog.class);
                startActivity(intent);
                //finish();
            }
        });

        SQLhelper GetSerList = new SQLhelper(Serializer.this);
        ArrayList SData = GetSerList.getSerData();

        if(SData.isEmpty()){
            myTv.setText("Нет данных для отображения");
        } else {
            BuildList(SData);
        }
    }

    public void BuildList(ArrayList SData){

            final ArrayList<HashMap<String, String>> listSera = new ArrayList<HashMap<String, String>>();

            for (int i = 0; i < SData.size(); i++){
                MySerial mySerial = (MySerial) SData.get(i);

                final HashMap<String, String> mapSer = new HashMap<String, String>();
                mapSer.put("SerName", mySerial.GetSerName());
                mapSer.put("SerSeas", mySerial.GetSerSeas());
                mapSer.put("SerEpi", mySerial.GetSerEpidode());
                mapSer.put("SerID", String.valueOf(mySerial.GetSerID()));

                listSera.add(mapSer);
            }

            final ListView serView = (ListView) findViewById(R.id.listView);
            final ListAdapter adapter = new SimpleAdapter(this, listSera, R.layout.model_list_serial, new String[]{"SerName", "SerSeas", "SerEpi"}, new int[]{R.id.textView3, R.id.textView5, R.id.textView7});
            serView.setAdapter(adapter);

            serView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            String SerNameI = listSera.get(position).get("SerName");
                            String SerSeasI = listSera.get(position).get("SerSeas");
                            String SerEpiI = listSera.get(position).get("SerEpi");
                            String SerID = listSera.get(position).get("SerID");

                            Intent intent = new Intent(Serializer.this, TrinDialog.class);
                            intent.putExtra("SerNameI", SerNameI);
                            intent.putExtra("SerSeasI", SerSeasI);
                            intent.putExtra("SerEpiI", SerEpiI);
                            intent.putExtra("SerID", SerID);
                            startActivity(intent);
                            finish();
                        }
                    }
            );
    }
}
