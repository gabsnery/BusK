package Fragments;

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
import com.example.gabi.busk_13_09.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import POJO.ObjHorario;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gabi on 18/09/2016.
 */
public class Fragment_Horarios extends Fragment{
    LinearLayout myLayout;
    View rootView;
    LinearLayout.LayoutParams lp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String idOnibus = this.getArguments().getString("idOnibus");
        String idDia = this.getArguments().getString("idDia");

        rootView = inflater.inflate(R.layout.fragment_horarios, container, false);

        JSONObject json=new JSONObject();
        try {
            json.put("idOnibus",idOnibus);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        lp= new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        myLayout = (LinearLayout) rootView.findViewById(R.id.fragHora);
        final TextView textSegunda = (TextView) rootView.findViewById(R.id.textViewSegunda);
        final TextView textSabado = (TextView) rootView.findViewById(R.id.textViewSabado);
        final TextView textDomingo = (TextView) rootView.findViewById(R.id.textViewDomingo);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://gabsnery-001-site1.ftempurl.com").build();

        // RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://192.168.25.232").build();
        OnibusAPI api = adapter.create(OnibusAPI.class);
        textSegunda.setText("");
                textSabado.setText("");
        textDomingo.setText("");
        api.retornaHorarios(json.toString(),
                new Callback<List<ObjHorario>>() {
                    @Override
                    public void success(List<ObjHorario> response, Response response2) {
                        List<ObjHorario> lista=response;
                        for (ObjHorario p:lista
                                ) {

                            if (p.getDia()==0)
                            {
                                textSegunda.setText(textSegunda.getText()+""+p.getHora()+" ");

                            }
                            if (p.getDia()==1)
                            {
                                textSabado.setText(textSabado.getText()+""+p.getHora()+" ");

                            } if (p.getDia()==2)
                            {
                                textDomingo.setText(textDomingo.getText()+" "+p.getHora()+" ");

                            }

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
