package Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.gabi.busk_13_09.OnibusAPI;
import com.example.gabi.busk_13_09.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import POJO.ObjMensagem;
import adapter.adapterMensagem;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gabi on 13/09/2016.
 */
public class Fragment_Mensagens  extends Fragment {
    ListView viewOni;
    Button botaoEnvia;
    EditText edtNome;
    EditText edtComentario;
    String idOnibus;
    Spinner spinner;
    public void atualiza()
    {
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://gabsnery-001-site1.ftempurl.com").build();
        // RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://192.168.25.232").build();
        idOnibus = this.getArguments().getString("idOnibus");
        JSONObject json=new JSONObject();
        try {
            json.put("idOnibus",idOnibus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OnibusAPI api = adapter.create(OnibusAPI.class);
        api.retornaMensagens(json.toString(), new Callback<List<ObjMensagem>>() {
            @Override
            public void success(List<ObjMensagem> objMensagems, Response response) {
                List<ObjMensagem> lista  = objMensagems;
                adapterMensagem adapter=new adapterMensagem(getActivity(),lista);
                viewOni.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mensagem, container, false);
        viewOni = (ListView)rootView.findViewById(R.id.listView2);
        botaoEnvia= (Button)rootView.findViewById(R.id.botaoEnvia);
        edtNome= (EditText)rootView.findViewById(R.id.editTextNome);
        edtComentario= (EditText)rootView.findViewById(R.id.edittextComentario);
         spinner = (Spinner)rootView.findViewById(R.id.spinner);


getActivity();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         idOnibus = this.getArguments().getString("idOnibus");
        ArrayAdapter<CharSequence> adapter;
        if (idOnibus.equals("-1"))
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.planets_array, android.R.layout.simple_spinner_item);
        else
            adapter = ArrayAdapter.createFromResource(getActivity(),R.array.planets_array2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        atualiza();
        botaoEnvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String currentDateandTime = sdf.format(Calendar.getInstance().getTime());
                JSONObject json=new JSONObject();
                try {
                    json.put("idOnibus",idOnibus);
                    json.put("nome",edtNome.getText().toString());
                    json.put("comentario",edtComentario.getText().toString());
                    json.put("data",currentDateandTime);
                    json.put("textoPronto",spinner.getSelectedItem().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://gabsnery-001-site1.ftempurl.com").build();
                // RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://192.168.25.232").build();
                OnibusAPI api = adapter.create(OnibusAPI.class);
                api.insereMensagem(json.toString(),
                        new Callback<String>() {
                            @Override
                            public void success(String s, Response response) {
                                atualiza();
                                edtComentario.setText("");
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });

    }
});
    }}
