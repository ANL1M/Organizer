package anlim.organizer.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLhelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "organizer.db";
    public static final String TABLE_SER_LIST = "Serials";
    public static final String TABLE_SER_SET = "SettingsShow";
    public static final String KEY_ID = "id";
    public static final String KEY_SER_NAME = "SerName";
    public static final String KEY_SER_SEASON = "Season";
    public static final String KEY_SER_EPISOD = "Episode";
    public static final String KEY_SET_SEAS = "SeasonSett";
    public static final String KEY_SET_EPISOD = "EpisodeSett";

    public SQLhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SER = "CREATE TABLE " + TABLE_SER_LIST + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_SER_NAME + " TEXT, " + KEY_SER_SEASON + " TEXT, " + KEY_SER_EPISOD + " TEXT" + ") ";
        String CREATE_TABLE_SET = "CREATE TABLE " + TABLE_SER_SET + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_SET_SEAS + " TEXT, " + KEY_SET_EPISOD + " TEXT" + ") ";
        db.execSQL(CREATE_TABLE_SER);
        db.execSQL(CREATE_TABLE_SET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SER_LIST + TABLE_SER_SET);
        onCreate(db);
    }

    public void addSerInfo(MySerial mySerial){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SQLhelper.KEY_SER_NAME, mySerial.GetSerName());
        values.put(SQLhelper.KEY_SER_SEASON, mySerial.GetSerSeas());
        values.put(SQLhelper.KEY_SER_EPISOD, mySerial.GetSerEpidode());

        db.insert(SQLhelper.TABLE_SER_LIST, null, values);
        db.close();
    }

    public ArrayList<MySerial> getSerData(){

        ArrayList<MySerial> listSerials = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_SER_LIST, null, null,  null, null, null, null);

        if (c.moveToFirst()){
            do{
                MySerial mySerial = new MySerial();

                mySerial.SetSerName(c.getString(1));
                mySerial.SetSerSeas(c.getString(2));
                mySerial.SetSerEpidode(c.getString(3));
                mySerial.SetID(c.getInt(0));
                listSerials.add(mySerial);

            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return listSerials;
    }

    public int updateSerData(MySerial mySerial){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SQLhelper.KEY_SER_NAME, mySerial.GetSerName());
        values.put(SQLhelper.KEY_SER_SEASON, mySerial.GetSerSeas());
        values.put(SQLhelper.KEY_SER_EPISOD, mySerial.GetSerEpidode());

        return db.update(TABLE_SER_LIST, values, KEY_ID + " = ?",
                new String[] { String.valueOf(mySerial.GetSerID()) });
    }

    public void deleteSerData(MySerial mySerial){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SER_LIST, KEY_ID + " = ?", new String[]{String.valueOf(mySerial.GetSerID())});
        db.close();

    }

    public List<String> getCSeas() {
        List<String> listSettings = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_SER_SET;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()){
            String string;
            string = c.getString(1);
            listSettings.add(string);
            string = c.getString(2);
            listSettings.add(string);
        } while (c.moveToNext())

            c.close();
            db.close();
            return listSettings;
    }

    public void addData(List<String> showSettings){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_SET_SEAS, showSettings.get(0));
        cv.put(KEY_SET_EPISOD, showSettings.get(1));
        db.delete(TABLE_SER_SET, null, null);
        db.insert(TABLE_SER_SET, null, cv);
        db.close();
    }

}
