package com.example.gabi.busk_13_09.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gabi.busk_13_09.OnibusAPI;
import com.example.gabi.busk_13_09.POJO.ObjHorario;
import com.example.gabi.busk_13_09.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gabi on 18/09/2016.
 */
public class Fragment_Horarios extends Fragment{
    LinearLayout myLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String idOnibus = this.getArguments().getString("idOnibus");
        View rootView = inflater.inflate(R.layout.fragment_lista_onibus, container, false);
        myLayout = (LinearLayout) rootView.findViewById(R.id.fragHorario);
        JSONObject json=new JSONObject();
        try {
            json.put("idOnibus",idOnibus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://192.168.25.232").build();
        OnibusAPI api = adapter.create(OnibusAPI.class);
        api.retornaHorarios(json.toString(),
                new Callback<List<ObjHorario>>() {
                    @Override
                    public void success(List<ObjHorario> response, Response response2) {
                        List<ObjHorario> lista=response;
                        for (ObjHorario p:lista
                                ) {


                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            TextView a = new TextView(getActivity());
                            a.setTextSize(15);
                            a.setLayoutParams(lp);
                            a.setId(Integer.parseInt("3"));
                            a.setText((1) + ": something");
                            myLayout.addView(a);

                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("JSON", error.toString());
                    }
                });
        return  rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
