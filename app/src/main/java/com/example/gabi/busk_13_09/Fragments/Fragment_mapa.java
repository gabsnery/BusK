package com.example.gabi.busk_13_09.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabi.busk_13_09.OnibusAPI;
import com.example.gabi.busk_13_09.POJO.ObjPonto;
import com.example.gabi.busk_13_09.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by gabi on 12/09/2016.
 */
public class Fragment_mapa extends Fragment implements OnMapReadyCallback {
    List<ObjPonto> lista;
    String idOnibus;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        idOnibus = this.getArguments().getString("idOnibus");
        return inflater.inflate(R.layout.fragment_gmaps, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idOnibus = this.getArguments().getString("idOnibus");
        MapFragment fragment = new MapFragment();
        fragment.getMapAsync(this);
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.map_layout, fragment, "mapfragment");
        transaction.commit();


        //MapFragment fragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        //fragment.getMapAsync(this);
        //((MapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {



        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-22.7158, -47.773), 11.0f));




        JSONObject json=new JSONObject();
        try {
            json.put("idOnibus",idOnibus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://192.168.25.232").build();
        OnibusAPI api = adapter.create(OnibusAPI.class);
        api.retornaPontos(json.toString(),
                new Callback<List<ObjPonto>>() {
                    @Override
                    public void success(List<ObjPonto> response, Response response2) {
                        List<ObjPonto> lista=response;
                        for (ObjPonto p:lista
                             ) {
                            p.getId();
                            String aspas= "\"";
                            try {
                                googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble((p.getLatitude()).replace(aspas, "").replace(",",".")), Double.parseDouble(p.getLongitude().replace(aspas, "").replace(",",".")))));
                            }catch (Exception e) {
                                Log.d("tag",e.toString());
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
