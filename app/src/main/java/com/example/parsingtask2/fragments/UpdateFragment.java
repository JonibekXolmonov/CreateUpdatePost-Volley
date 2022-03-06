package com.example.parsingtask2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.parsingtask2.Logger;
import com.example.parsingtask2.R;
import com.example.parsingtask2.model.Post;
import com.google.android.material.button.MaterialButton;


public class UpdateFragment extends Fragment implements View.OnClickListener {

    private EditText edtTitle;
    private EditText edtBody;
    private NavController navController;
    private Post post;
    private Integer postPosition;

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey("postEdit") && getArguments().containsKey("postPosition")) {
            post = (Post) (getArguments().getParcelable("postEdit"));
            postPosition = getArguments().getInt("postPosition");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    private void initViews(View view) {
        edtTitle = view.findViewById(R.id.edtTitle);
        edtBody = view.findViewById(R.id.edtBody);
        MaterialButton btnPost = view.findViewById(R.id.btnEdit);
        navController = Navigation.findNavController(view);

        edtTitle.setText(post.title);
        edtBody.setText(post.body);

        btnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnEdit) {
            Bundle bundle = new Bundle();
            bundle.putInt("postPosition", postPosition);
            bundle.putParcelable("postEdited", getEditedPost());
            navController.navigate(R.id.action_updateFragment_to_mainFragment, bundle);
        }
    }

    private Post getEditedPost() {
        return new Post(post.id, post.userId, edtTitle.getText().toString(), edtBody.getText().toString());
    }
}