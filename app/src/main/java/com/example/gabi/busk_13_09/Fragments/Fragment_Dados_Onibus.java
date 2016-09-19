package com.example.gabi.busk_13_09.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabi.busk_13_09.OnibusAPI;
import com.example.gabi.busk_13_09.POJO.ObjOnibus;
import com.example.gabi.busk_13_09.R;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gabi on 13/09/2016.
 */
public class Fragment_Dados_Onibus extends Fragment{
    TextView lal;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String idOnibus = this.getArguments().getString("idOnibus");
         View rootView = inflater.inflate(R.layout.fragment_onibus, container, false);
        lal=(TextView)rootView.findViewById(R.id.textView2);

        JSONObject json=new JSONObject();
        try {
            json.put("idOnibus",idOnibus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://192.168.25.232").build();
        OnibusAPI api = adapter.create(OnibusAPI.class);
        api.selectOnibus(json.toString(),
                new Callback<ObjOnibus>() {
                    @Override
                    public void success(ObjOnibus response, Response response2) {

                        lal.setText(response.getNome());
                        Log.d("JSON", response.getNome());

                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("JSON", error.toString());
                    }
                });
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    }
