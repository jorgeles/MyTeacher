package com.example.jmrosell.myteacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jmrosell.myteacher.Games.Game;

import java.util.ArrayList;

/**
 * Created by jorgeleonfernandez on 5/5/15.
 */
public class CustomListAdapter extends BaseAdapter {
    private ArrayList<Game> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<Game> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Game getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView texto1 = null;
        TextView texto2 = null;
        ImageView imagen = null;
        System.out.println("Hola");
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            texto1 = (TextView) convertView.findViewById(R.id.texto1);
            texto2 = (TextView) convertView.findViewById(R.id.texto2);
            imagen = (ImageView) convertView.findViewById(R.id.imagen);
            //convertView.setTag(holder);
        } else {
            //holder = (ViewHolder) convertView.getTag();
        }
        texto1.setText(listData.get(position).getNombre());
        texto2.setText("By, " + listData.get(position).getDescripcion());
        //imagen.setImageAlpha(R.drawable.background);
        return convertView;
    }
}
