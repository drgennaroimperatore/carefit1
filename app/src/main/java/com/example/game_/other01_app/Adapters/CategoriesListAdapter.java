package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.game_.other01_app.Database.entities.Category;
import com.example.game_.other01_app.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CategoryViewHolder> {

    private final ArrayList<String> interested;
    private final ArrayList<String> notInterested;
    private final boolean changingPreferences;

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox categoryItemCheckBox;
        private CategoryViewHolder(View itemView) {
            super(itemView);
            categoryItemCheckBox = itemView.findViewById(R.id.categoryCheckBox);
        }
    }

    private final LayoutInflater mInflater;
    private List<Category> mCategories; //Cached copy of categories

    public CategoriesListAdapter(Context context, boolean changingPreferences){
        this.changingPreferences = changingPreferences;
        Context adapterContext = context;
        mInflater = LayoutInflater.from(context);
        interested = new ArrayList<>();
        notInterested = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.category_recyclerview_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        if(mCategories != null) {
            Category current = mCategories.get(position);
            holder.categoryItemCheckBox.setText(current.getCategory());
            if(changingPreferences) {
                holder.categoryItemCheckBox.setChecked(current.isInterested());
                if (current.isInterested()) {
                    interested.add(current.getCategory());
                } else {
                    notInterested.add(current.getCategory());
                }
            }
                holder.categoryItemCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (holder.categoryItemCheckBox.isChecked()) {
                            interested.add(current.getCategory());
                            notInterested.remove(current.getCategory());
                        } else if (!holder.categoryItemCheckBox.isChecked()) {
                            notInterested.add(current.getCategory());
                            interested.remove(current.getCategory());
                        }
                    }
                });
        } else {
            holder.categoryItemCheckBox.setText(R.string.warning_no_categories);
            holder.categoryItemCheckBox.setClickable(false);
        }
    }

    public ArrayList<String> getInterested(){
        return interested;
    }

    public ArrayList<String> getNotInterested() {
        return notInterested;
    }

    public void setmCategories(List<Category> categories){
        mCategories = categories;
        notifyDataSetChanged();
    }

    // getItemCount is called many times, and when it is first called,
    // mCategories has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCategories != null)
            return mCategories.size();
        else return 0;
    }
}
