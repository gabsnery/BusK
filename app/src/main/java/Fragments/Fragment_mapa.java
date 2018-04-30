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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import POJO.ObjPonto;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by gabi on 12/09/2016.
 */
public class Fragment_mapa extends Fragment implements OnMapReadyCallback{

    List<ObjPonto> lista;
    String idOnibus;
    String StreetName;
GoogleMap googleMap2;
    boolean ponto=false;
    LatLng origem;
    LatLng destino;
    Button botao;
    Button botaoCancela;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        idOnibus = this.getArguments().getString("idOnibus");
        View rootView = inflater.inflate(R.layout.fragment_gmaps, container, false);
        botao= (Button)rootView.findViewById(R.id.button);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idOnibus = this.getArguments().getString("idOnibus");
        MapFragment fragment = new MapFragment();
        fragment.getMapAsync(this);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.map_layout, fragment, "mapfragment");
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

            botao.setVisibility(View.GONE);
            JSONObject json = new JSONObject();
            try {
                json.put("idOnibus", idOnibus);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://gabsnery-001-site1.ftempurl.com").build();
            OnibusAPI api = adapter.create(OnibusAPI.class);
            api.retornaPontos(json.toString(),
                    new Callback<ArrayList<ObjPonto>>() {
                        @Override
                        public void success(ArrayList<ObjPonto> response, Response response2) {

                            lista = response;

                            Collections.sort(lista, ObjPonto.DESCENDING_COMPARATOR);
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

                                    if (p.getIda() == 1) {
                                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.mark);
                                        b = bitmapdraw.getBitmap();
                                        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                                    }
                                    if (p.getIda() == 0) {
                                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.mark2);
                                        b = bitmapdraw.getBitmap();
                                        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                                    }
                                    marcador = googleMap.addMarker(new MarkerOptions().position(coordenada).title(String.valueOf(p.getOrdem()) + ":" + StreetName).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

                                    p.setMarcador(marcador);
                                } catch (Exception e) {
                                    Log.d("tag", e.toString());
                                }
                            }


                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("JSON", error.toString());
                        }
                    });

    }


}



