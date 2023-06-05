package ru.gozhan.lab06.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ru.gozhan.lab06.R;
import ru.gozhan.lab06.model.Sight;

public class SightListAdapter extends ArrayAdapter<Sight> {

    private Context context;
    private List<Sight> sights;

    public SightListAdapter(Context context, List<Sight> sights) {
        super(context, 0, sights);
        this.context = context;
        this.sights = sights;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_sight, parent, false);
        }

        Sight currentSight = sights.get(position);

        TextView titleTextView = listItemView.findViewById(R.id.sightTitle);
        titleTextView.setText(currentSight.getTitle());

        TextView descriptionTextView = listItemView.findViewById(R.id.sightDescription);
        descriptionTextView.setText(currentSight.getFullDescription());

        return listItemView;
    }
}
