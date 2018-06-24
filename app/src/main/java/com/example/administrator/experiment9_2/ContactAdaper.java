package com.example.administrator.experiment9_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */

public class ContactAdaper extends ArrayAdapter<Contacts>{
    private int resourceId;
    public ContactAdaper(Context context, int textViewResourceId, List<Contacts> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contacts contacts=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView text_name=(TextView) view.findViewById(R.id.text_name);
        TextView text_number=(TextView) view.findViewById(R.id.text_number);
        TextView text_sex=(TextView) view.findViewById(R.id.text_sex);

        text_name.setText(contacts.getName());
        text_number.setText(contacts.getNumber());
        text_sex.setText(contacts.getSex());

        return view;
    }

}
