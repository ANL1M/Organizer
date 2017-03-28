package anlim.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Instruments extends AppCompatActivity {
    ImageButton Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruments);
        Switch = (ImageButton) findViewById(R.id.imageButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean ret;
        if (item.getItemId() == R.id.action_settings) {
            ret = true;
            Intent intent = new Intent(this, MainSettings.class);
            startActivity(intent);
        } else {
            ret = super.onOptionsItemSelected(item);
        }
        return ret;
    }

    public void toGenerator(View v){
        Intent intent = new Intent(this, Generator.class);
        startActivity(intent);
    }

    public void toFlashlight(View v){
        Intent intent = new Intent(this, Flashlight.class);
        startActivity(intent);
    }

    public void toEnumerator(View v){
        Intent intent = new Intent(this, Enumerator.class);
        startActivity(intent);
    }

    public void toSerializer(View v){
        Intent intent = new Intent(this, Serializer.class);
        startActivity(intent);
    }
}
