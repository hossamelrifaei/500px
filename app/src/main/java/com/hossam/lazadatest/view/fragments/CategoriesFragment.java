package com.hossam.lazadatest.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hossam.lazadatest.R;
import com.hossam.lazadatest.adapter.CategoriesAdapter;
import com.hossam.lazadatest.adapter.OnItemRecycleViewClickListener;
import com.hossam.lazadatest.model.utiles.Utils;

/**
 * A placeholder fragment containing a simple view.
 */
public class CategoriesFragment extends Fragment implements OnItemRecycleViewClickListener<CategoriesAdapter> {

    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public CategoriesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_categories, container, false);
        categoriesRecyclerView = (RecyclerView) v.findViewById(R.id.categories_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        categoriesRecyclerView.setLayoutManager(layoutManager);
        categoriesAdapter = new CategoriesAdapter(this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
        getActivity().setTitle(getString(R.string.categories_title));

        return v;
    }

    @Override
    public void onItemClicked(int position, CategoriesAdapter mAdapter, ImageView imageView) {


        CategoryPhotosFragment categoryPhotosFragment = new CategoryPhotosFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Utils.CATEGORY_TAG, mAdapter.getItem(position));
        categoryPhotosFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, categoryPhotosFragment)
                .addToBackStack(Utils.BACK_STACK_TAG)
                .commit();
    }


}
