package Fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabi.busk_13_09.R;

/**
 * Created by gabi on 13/09/2016.
 */
public class Fragment_Main  extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fontes/HomegirlHugItOut.ttf");

        TextView tv = (TextView) rootView.findViewById(R.id.textView7);
        tv.setTypeface(type);
        TextView tv2 = (TextView) rootView.findViewById(R.id.textView8);
        tv2.setTypeface(type);
        TextView tv3 = (TextView) rootView.findViewById(R.id.textView9);
        tv3.setTypeface(type);
        TextView tv4 = (TextView) rootView.findViewById(R.id.textView3);
        tv4.setTypeface(type);
        TextView tv5 = (TextView) rootView.findViewById(R.id.textView4);
        tv5.setTypeface(type);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
