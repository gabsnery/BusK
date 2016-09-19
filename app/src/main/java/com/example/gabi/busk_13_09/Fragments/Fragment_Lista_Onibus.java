package com.example.gabi.busk_13_09.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gabi.busk_13_09.Main2Activity;
import com.example.gabi.busk_13_09.OnibusAPI;
import com.example.gabi.busk_13_09.POJO.ObjOnibus;
import com.example.gabi.busk_13_09.R;
import com.example.gabi.busk_13_09.listaAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by gabi on 13/09/2016.
 */
public class Fragment_Lista_Onibus  extends Fragment {
    //ListView viewOni;
    listaAdapter adapter;
    ListView  viewOni=null;
    private ArrayList<HashMap<String,String>> callLog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_onibus, container, false);

        viewOni = (ListView)rootView.findViewById(R.id.listView);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://192.168.25.232").build();
        OnibusAPI api = adapter.create(OnibusAPI.class);


        api.getBuses(new Callback<List<ObjOnibus>>() {
            @Override
            public void success(List<ObjOnibus> objOnibuses, Response response) {
                List<ObjOnibus> lista  = objOnibuses;
                listaAdapter adapter=new listaAdapter(getActivity(),lista);
                viewOni.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Fodeu!!"+error.toString(), Toast.LENGTH_SHORT).show();
            }


        });
        viewOni.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("oi", String.valueOf(position)+"     "+parent.getAdapter().getItem(position));
                ObjOnibus on= (ObjOnibus) parent.getAdapter().getItem(position);
                Intent i = new Intent(getActivity(), Main2Activity.class);
                i.putExtra("id_onibus", on.getId());
                startActivity(i);

            }
        });
            return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
