package POJO;

import com.google.android.gms.maps.model.Marker;

import java.util.Comparator;

/**
 * Created by gabi on 02/09/2016.
 */
public class ObjPonto {
    private String Latitude;
    private String Longitude;
    private int Id;

    public Marker getMarcador() {
        return marcador;
    }

    public void setMarcador(Marker marcador) {
        this.marcador = marcador;
    }

    private Marker marcador;

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    private int ordem;

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public static final Comparator<ObjPonto> DESCENDING_COMPARATOR = new Comparator<ObjPonto>() {
        // Overriding the compare method to sort the age
        public int compare(ObjPonto d, ObjPonto d1) {
            return d.ordem - d1.ordem;
        }
    };
    private int ida;

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
