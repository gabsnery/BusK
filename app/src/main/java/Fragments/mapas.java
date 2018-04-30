package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gabi.busk_13_09.OnibusAPI;
import com.example.gabi.busk_13_09.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import POJO.ObjPonto;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by gabi on 13/09/2016.
 */
public class mapas  extends Fragment implements OnMapReadyCallback {
    List<ObjPonto> lista;

    String StreetName;
    GoogleMap googleMap2;
    boolean ponto = false;
    LatLng origem;
    LatLng destino;
    Button botao;
    Button botaoCancela;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gmap_todos_pontos, container, false);
        botao = (Button) rootView.findViewById(R.id.button);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment fragment = new MapFragment();
        fragment.getMapAsync(this);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.map_layout2, fragment, "mapfragment");
        transaction.commit();


        //MapFragment fragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        //fragment.getMapAsync(this);
        //((MapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        googleMap2 = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Add a marker in Sydney and move the camera


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-22.729289, -47.647174), 12.0f));



            Trakker mGPS = new Trakker(getActivity());


            if (mGPS.canGetLocation) {
                mGPS.getLocation();
                Toast.makeText(getActivity(), "Lat" + mGPS.getLatitude() + "Lon" + mGPS.getLongitude(), Toast.LENGTH_SHORT).show();
                googleMap.addMarker(new MarkerOptions().position(new LatLng(mGPS.getLatitude(), mGPS.getLongitude())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("Olha voce aqui!!!"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mGPS.getLatitude(), mGPS.getLongitude())));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mGPS.getLatitude(), mGPS.getLongitude()), 16.0f));
            } else {

                Toast.makeText(getActivity(), "Por favor.Liga o GPS!", Toast.LENGTH_SHORT).show();

                System.out.println("Unable");
            }

            RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://gabsnery-001-site1.ftempurl.com").build();
            OnibusAPI api = adapter.create(OnibusAPI.class);
            api.getPontos(new Callback<List<ObjPonto>>() {
                @Override
                public void success(List<ObjPonto> response, Response response2) {

                    lista = response;
                    String aspas = "\"";
                    for (ObjPonto p : lista) {
                        LatLng coordenada = new LatLng(Double.parseDouble((p.getLatitude()).replace(aspas, "").replace(",", ".")), Double.parseDouble(p.getLongitude().replace(aspas, "").replace(",", ".")));
                        p.getId();


                        try {
                            int height = 39;
                            int width = 24;
                            Marker marcador;
                            BitmapDrawable bitmapdraw;
                            Bitmap smallMarker = null;
                            Bitmap b;
                            Geocoder gcd = new Geocoder(getActivity(), Locale.getDefault());
                            List<android.location.Address> addresses = gcd.getFromLocation(Double.parseDouble((p.getLatitude()).replace(aspas, "").replace(",", ".")), Double.parseDouble(p.getLongitude().replace(aspas, "").replace(",", ".")), 1);
                            if (addresses.size() > 0)
                                StreetName = addresses.get(0).getThoroughfare();

                            bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.mark2);
                            b = bitmapdraw.getBitmap();
                            smallMarker = Bitmap.createScaledBitmap(b, width, height, false);


                            marcador = googleMap.addMarker(new MarkerOptions().position(coordenada).title(String.valueOf(p.getOrdem()) + ":" + StreetName).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

                            p.setMarcador(marcador);
                        } catch (Exception e) {
                            Log.d("tag", e.toString());
                        }
                    }

                    Toast.makeText(getActivity(), "Selecione o ponto que você quer pegar!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("JSON", error.toString());
                }
            });
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Toast.makeText(getActivity(), "oi", Toast.LENGTH_SHORT).show();
                    if (ponto == false) {
                        ponto = true;
                        origem = marker.getPosition();
                        Toast.makeText(getActivity(), "Para onde você quer ir?", Toast.LENGTH_LONG).show();
                    } else {
                        ponto = false;
                        destino = marker.getPosition();

                    }

                    return false;
                }
            });


        }




}