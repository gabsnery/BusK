package adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gabi.busk_13_09.R;

import java.util.List;

import POJO.ObjOnibus;


/**
 * Created by Gabi on 15/05/2016.
 */
public class listaAdapter extends ArrayAdapter<ObjOnibus>
{
    public Typeface mycustomFont;
    Context context;
    List<ObjOnibus> listinha;
    public listaAdapter(Context context, List<ObjOnibus> listinha)
    {
        super(context, 0, listinha);
        this.context=context;
        this.listinha=listinha;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ObjOnibus bus = this.listinha.get(position);
        convertView= LayoutInflater.from(this.context).inflate(R.layout.bus_item,null);

        TextView textoNome = (TextView) convertView.findViewById(R.id.nomeBus);
        textoNome.setText(bus.getNome());


        TextView textoTipo = (TextView) convertView.findViewById(R.id.linhaBus);
        textoTipo.setText(bus.getLinha());


        TextView textoId = (TextView) convertView.findViewById(R.id.letraBus);
        textoId.setText(String.valueOf(bus.getId()));



        return  convertView;
    }



}
