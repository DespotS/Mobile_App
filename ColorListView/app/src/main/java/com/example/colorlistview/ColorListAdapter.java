package com.example.colorlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ColorListAdapter extends BaseAdapter {

    private ColorEntry[] data;

    public ColorListAdapter(ColorEntry[] colors){
        this.data= colors;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //get the data
        ColorEntry entry = data[i];

        //if no view for the layout exists yet to recycle...

        if(view ==null){

        //create in memory data from layout file color list_view_entry.xml
        Context context = viewGroup.getContext();
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.color_list_view_entry, null, false);
    }


       //set color of the box
       FrameLayout colorBox =  view.findViewById(R.id.list_entry_color_box);
        colorBox.setBackgroundColor(entry.colorCode);

        //set the text

        TextView textView = view.findViewById(R.id.list_entry_text_view);
        textView.setText(entry.name);

        //Return the view
        return view;
    }
}
