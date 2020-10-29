package com.example.pantryapp.ui.recipebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pantryapp.MyAdapter;
import com.example.pantryapp.R;
import com.example.pantryapp.databinding.FragmentRecipebookBinding;
import com.example.pantryapp.pantry.Ingredient;
import com.example.pantryapp.pantry.Recipe;
import com.example.pantryapp.pantry.options.Units;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecipeBookFragment extends Fragment {

    private RecipeBookViewModel recipeBookViewModel;

    private ArrayList<RecipeModel> mRecipeBook = new ArrayList<>();
    private RecyclerView mRecyclerView2;
    //private MyAdapter mAdapter;
    private ModelAdapter mAdapter;
    private SearchView searchView;
    FragmentRecipebookBinding binding;

    private static final Comparator<RecipeModel> ALPHABETICAL_COMPARATOR = (o1, o2) -> o1.getRecipe().getName().compareTo(o2.getRecipe().getName());

    //View view;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //recipeBookViewModel = new ViewModelProvider(this).get(RecipeBookViewModel.class);
        //View root = inflater.inflate(R.layout.fragment_recipebook, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipebook, container, false);
        View root = binding.getRoot();

        mAdapter = new ModelAdapter(getActivity(), ALPHABETICAL_COMPARATOR);
        mRecyclerView2 = root.findViewById(R.id.recyclerView2);
        mRecyclerView2.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//        mRecyclerView2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                if(bottom > oldBottom) {
//                    mRecyclerView2.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mRecyclerView2.getLayoutParams().height = 100;
//                        }
//                    }, 100);
//                }

//                if(bottom > oldBottom) {
//                    mRecyclerView2.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mRecyclerView2.getLayoutParams().height = 300;
//                        }
//                    }, 100);
//                }
          //  }
       // });

        //mRecyclerView2.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, 100));

        searchView = root.findViewById(R.id.SearchView1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;

            }

            @Override
            public boolean onQueryTextChange(String query) {
                final ArrayList<RecipeModel> filteredModelList = filter(mRecipeBook, query);
                mAdapter.replaceAll(filteredModelList);
                binding.recyclerView2.scrollToPosition(0);
                return true;
            }
        });

        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        //binding.recyclerView2.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, 100));
        binding.recyclerView2.setAdapter(mAdapter);


        mAdapter.add(mRecipeBook);
        prepareData();

        Button btn = (Button)root.findViewById(R.id.button9);
        btn.setOnClickListener(v -> {
            Recipe temp = new Recipe("created");
            temp.addIngredient("createdCheese", 40, Units.LBS);
            mRecipeBook.add(new RecipeModel(200, temp));

            mAdapter.notify(mRecipeBook);
        });



        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


//        final TextView textView = root.findViewById(R.id.text_recipebook);
//        recipeBookViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
  //      });
        return root;
    }

//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        //mAdapter = new MyAdapter(getActivity(), mRecipeBook);
//
//        //mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        //mRecyclerView2.setHasFixedSize(true);
//        //mRecyclerView1.setItemAnimator(new DefaultItemAnimator());
//        //mRecyclerView2.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//        //mRecyclerView2.setAdapter(mAdapter);
//        //prepareData();
//        //mRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
//    }

    private void prepareData() {
        ArrayList<Ingredient> tempList = new ArrayList<>();
        tempList.add(new Ingredient("Cheese", 30, Units.UNITS));

        for(int i = 0; i < 21; i++) {
            mRecipeBook.add(new RecipeModel(i, new Recipe("r" + i, tempList)));
        }

        mAdapter.notify(mRecipeBook);
    }

    private static ArrayList<RecipeModel> filter(ArrayList<RecipeModel> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final ArrayList<RecipeModel> filteredModelList = new ArrayList<>();
        for(RecipeModel model : models) {
            final String text = model.getRecipe().getName().toLowerCase();
            if(text.contains(lowerCaseQuery))
                filteredModelList.add(model);
        }

        return filteredModelList;
    }
}

