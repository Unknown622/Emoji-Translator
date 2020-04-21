package com.example.emojitranslator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements
        Filterable
{
    private ArrayList<ExampleItem> exampleItems, exampleItemsFull;

    public static class ExampleViewHolder extends  RecyclerView.ViewHolder
    {
        public TextView emoji, description;

        public ExampleViewHolder(@NonNull View itemView)
        {
            super(itemView);

            emoji = itemView.findViewById(R.id.emoji);
            description = itemView.findViewById(R.id.description);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> list)
    {
        exampleItems = list;
        exampleItemsFull = new ArrayList<>(exampleItems);
    }

    @NonNull
    @Override
    public ExampleAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent,
                false);

        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(v);
        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleAdapter.ExampleViewHolder holder, int position)
    {
        ExampleItem currentItem = exampleItems.get(position);

        holder.emoji.setText(currentItem.getEmoji());
        holder.description.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount()
    {
        return exampleItems.size();
    }

    private Filter exampleFilter = new Filter()
    {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence)
        {
            ArrayList<ExampleItem> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0)
            {
                filteredList.addAll(exampleItemsFull);
            }

            else
            {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (ExampleItem item : exampleItemsFull)
                {
                    if (item.getDescription().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            exampleItems.clear();
            exampleItems.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter()
    {
        return exampleFilter;
    }
}
