package com.example.cafeteria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<Menu> {

    private Context context;
    private int resource;

    public MenuAdapter(Context context, int resource, ArrayList<Menu> menuList) {
        super(context, resource, menuList);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Menu menu = getItem(position);

        if (menu != null) {
            TextView itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
            TextView priceTextView = convertView.findViewById(R.id.priceTextView);

          //  itemNameTextView.setText(menu.getItemName());
          //  priceTextView.setText(String.valueOf(menu.getPrice()));
        }

        return convertView;
    }
}
