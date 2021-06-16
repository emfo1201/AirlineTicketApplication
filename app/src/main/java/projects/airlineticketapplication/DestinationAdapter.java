package projects.airlineticketapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DestinationAdapter extends ArrayAdapter<DestinationItem> {
    private final List<DestinationItem> destinationList;

    public DestinationAdapter(@NonNull Context context, @NonNull List<DestinationItem> destinationItems) {
        super(context, 0, destinationItems);
        destinationList = new ArrayList<>(destinationItems);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return destinationFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.destinations_auto, parent, false
            );
        }

        TextView textViewDestination = convertView.findViewById(R.id.destination);
        TextView textViewDestinationInfo = convertView.findViewById(R.id.destinationInfo);
        TextView textViewDestinationId = convertView.findViewById(R.id.destinationId);

        DestinationItem destinationItem = getItem(position);

        if(destinationItem != null) {
            textViewDestination.setText(destinationItem.getDestination());
            textViewDestinationInfo.setText(destinationItem.getDestinationInfo());
            textViewDestinationId.setText(destinationItem.getDestinationId());
        }

        return convertView;
    }

    private final Filter destinationFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<DestinationItem> suggestions = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0) {
                suggestions.addAll(destinationList);
            } else {
                String filter = charSequence.toString().toLowerCase().trim();
                for(DestinationItem item : destinationList) {
                    if(item.getDestination().toLowerCase().contains(filter)
                            || item.getDestinationInfo().toLowerCase().contains(filter)) {
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((DestinationItem) resultValue).getDestination();
        }
    };
}
