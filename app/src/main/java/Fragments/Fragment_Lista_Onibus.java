package Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gabi.busk_13_09.Main2Activity;
import com.example.gabi.busk_13_09.OnibusAPI;
import com.example.gabi.busk_13_09.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import POJO.ObjOnibus;
import adapter.listaAdapter;
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
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://gabsnery-001-site1.ftempurl.com").build();

       // RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://192.168.25.232").build();
        OnibusAPI api = adapter.create(OnibusAPI.class);


        api.getBuses(new Callback<List<ObjOnibus>>() {
            @Override
            public void success(List<ObjOnibus> objOnibuses, Response response) {
                List<ObjOnibus> lista  = objOnibuses;
                Collections.sort(lista, new Comparator<ObjOnibus>() {
                    @Override
                    public int compare(ObjOnibus lhs, ObjOnibus rhs) {
                        return lhs.getNome().compareTo(rhs.getNome());
                    }
                });
                listaAdapter adapter=new listaAdapter(getActivity(),lista);
                viewOni.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
            }


        });
        viewOni.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("oi", String.valueOf(position)+"     "+parent.getAdapter().getItem(position));
                ObjOnibus on= (ObjOnibus) parent.getAdapter().getItem(position);
                Intent i = new Intent(getActivity(), Main2Activity.class);
                i.putExtra("id_onibus", on.getId());
                i.putExtra("nome", on.getNome());
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
