package com.test2.tstuff.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.test2.tstuff.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Tlist> {

    private List<Tlist> fullList;
    Context context;
    int resource;

    public ListAdapter(Context context, int resource, List<Tlist> list) {
        super(context, resource, list);
        this.context = context;
        this.fullList = list;
        this.resource = resource;


    }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            LayoutInflater layoutInflater = LayoutInflater.from(context);

            View view = layoutInflater.inflate(resource, null, false);

            TextView textView = view.findViewById(R.id.textView);
            TextView testView2 = view.findViewById(R.id.textView2) ;
            Tlist a = getItem(position);

            textView.setText(a.getTlist());
            testView2.setText("" + a.getQuantity());



        return view;
    }
}
