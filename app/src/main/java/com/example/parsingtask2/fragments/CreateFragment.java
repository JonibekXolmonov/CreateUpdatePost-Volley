package com.example.parsingtask2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.parsingtask2.R;
import com.example.parsingtask2.model.Post;
import com.google.android.material.button.MaterialButton;

public class CreateFragment extends Fragment implements View.OnClickListener {

    private EditText edtTitle;
    private EditText edtBody;
    private NavController navController;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
    }

    private void initViews(View view) {
        edtTitle = view.findViewById(R.id.edtTitle);
        edtBody = view.findViewById(R.id.edtBody);
        MaterialButton btnPost = view.findViewById(R.id.btnPost);
        navController = Navigation.findNavController(view);

        btnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPost) {
            if (!TextUtils.isEmpty(edtTitle.getText()) && !TextUtils.isEmpty(edtBody.getText())) {
                Post post = new Post(1, 1, edtTitle.getText().toString(), edtBody.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putParcelable("post", post);

                navController.navigate(R.id.action_createFragment_to_mainFragment, bundle);
            }
        }
    }
}