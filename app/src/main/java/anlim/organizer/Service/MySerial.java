package anlim.organizer.Service;

public class MySerial {

    String _SerName;
    String _SerSeas;
    String _SerEpisode;
    int _ID;

    public MySerial(){}

    public MySerial(String SerName, String SerSeas, String SerEpidode, int ID){
        this. _SerName = SerName;
        this. _SerSeas = SerSeas;
        this. _SerEpisode = SerEpidode;
        this. _ID = ID;
    }

    public void SetSerName(String SerName){
        this. _SerName = SerName;
    }

    public void SetSerSeas(String SerSeas){
        this. _SerSeas = SerSeas;
    }

    public void SetSerEpidode(String SerEpidode){
        this. _SerEpisode = SerEpidode;
    }

    public void SetID (int ID){
        this. _ID = ID;
    }

    public String GetSerName(){
        return this._SerName;
    }

    public String GetSerSeas(){
        return this._SerSeas;
    }

    public String GetSerEpidode(){
        return this._SerEpisode;
    }

    public int GetSerID(){
        return this._ID;
    }
}
