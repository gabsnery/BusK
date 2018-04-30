package adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gabi.busk_13_09.R;

import java.util.List;

import POJO.ObjMensagem;

/**
 * Created by gabi on 01/10/2016.
 */
public class adapterMensagem  extends ArrayAdapter<ObjMensagem>
{
    public Typeface mycustomFont;
    Context context;
    List<ObjMensagem> listinha;
    public adapterMensagem(Context context, List<ObjMensagem> listinha)
    {
        super(context, 0, listinha);
        this.context=context;
        this.listinha=listinha;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ObjMensagem bus = this.listinha.get(position);
        convertView= LayoutInflater.from(this.context).inflate(R.layout.mensagem_item,null);

        TextView textoNome = (TextView) convertView.findViewById(R.id.textViewNome);
        textoNome.setText(bus.getNome() );
        textoNome.setTextColor(Color.RED);

        TextView textoData = (TextView) convertView.findViewById(R.id.textViewData);
        textoData.setText(bus.getData() );
        textoData.setTextColor(Color.RED);


        TextView textoTipo = (TextView) convertView.findViewById(R.id.textViewMensagem);
        textoTipo.setText(bus.getMensagem());

        TextView textoSpin = (TextView) convertView.findViewById(R.id.textViewComentarioPronto);
        textoSpin.setText(bus.getTextoPronto() );
      //  textoSpin.setTextColor(Color.DKGRAY);
        textoSpin.setTextColor(Color.LTGRAY);


        return  convertView;
    }

}
