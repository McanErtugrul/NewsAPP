package com.example.mehme.haberappk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter extends BaseAdapter {

    private LayoutInflater inflater;
    private SimpleDateFormat format = new SimpleDateFormat("kk:mm:ss dd/MM/yyyy");
        private int deneme;
    private final List<Haber> items;


    public Adapter(Context context, List<Haber> items){
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = inflater.inflate(R.layout.haber_item, parent, false);

        final Haber item = items.get(position);
        ViewGroup container = view.findViewById(R.id.container);
        Button button = view.findViewById(R.id.openBtn);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), HaberActivity.class);
                intent.putExtra(HaberActivity.KEY_ITEM, item);
                parent.getContext().startActivity(intent);
            }
        });
        String deneme2=item.getHaberTur().toString();
     //   if("Ekonomi".equals(deneme2)) {

            // container.addView(createInputView(container, "ID", String.valueOf(item.getId())));
            container.addView(createInputView(container, "Baslik", item.getHaberBaslik()));
            //  container.addView(createInputView(container, "Icerik", item.getHaberIcerik()));
            container.addView(createInputView(container, "Tur", item.getHaberTur()));
            container.addView(createInputView(container, "Begenme", String.valueOf(item.getHaberBegenme())));
            container.addView(createInputView(container, "Begenmeme", String.valueOf(item.getHaberBegenmeme())));

            String stringDate = "";

            try {
                String value = item.getHaberYayinTarih().replaceAll("[^0-9]", "");
                long intValue = Long.parseLong(value);
                stringDate = format.format(new Date(intValue));
            } catch (Exception e) {
                e.printStackTrace();
            }

            container.addView(createInputView(container, "YayinTarih", stringDate));
            // container.addView(createInputView(container, "Goruntulenme", String.valueOf(item.getHaberGoruntulenme())));

     //   }


        return view;
    }

    private View createInputView(ViewGroup container, String label, String value){
        View inputView = inflater.inflate(R.layout.haber_item_input, container, false);
        TextView labelText = inputView.findViewById(R.id.label);
        labelText.setText(label);
        TextView valueText = inputView.findViewById(R.id.value);
        valueText.setText(value);
        return inputView;
    }
}
