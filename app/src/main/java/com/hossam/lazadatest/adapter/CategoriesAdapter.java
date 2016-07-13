package com.hossam.lazadatest.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hossam.lazadatest.R;
import com.hossam.lazadatest.model.pojo.Categories;
import com.hossam.lazadatest.view.interfaces.OnItemRecycleViewClickListener;

/**
 * Created by Hossam on 5/14/2016.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryItemViewHolder> {
    private OnItemRecycleViewClickListener mOnItemRecycleViewClickListener;
    private String[] mCategoriesList;

//


    public CategoriesAdapter(OnItemRecycleViewClickListener onItemRecycleViewClickListener) {
        mCategoriesList = Categories.CategoriesList;
        mOnItemRecycleViewClickListener = onItemRecycleViewClickListener;
    }


    public String getItem(int position) {

        return mCategoriesList[position];
    }

    @Override
    public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        CategoryItemViewHolder viewHolder = new CategoryItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryItemViewHolder holder, int position) {

        holder.categoryNameText.setText(mCategoriesList[position]);
        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemRecycleViewClickListener.onItemClicked(Integer.parseInt(view.getTag().toString()), CategoriesAdapter.this, null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCategoriesList.length;
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView categoryNameText;

        public CategoryItemViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            categoryNameText = (TextView) itemView.findViewById(R.id.category_name_text);
        }
    }
}
