package com.example.parsingtask2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.parsingtask2.Logger;
import com.example.parsingtask2.R;
import com.example.parsingtask2.adapter.PostAdapter;
import com.example.parsingtask2.helper.ItemClickListener;
import com.example.parsingtask2.helper.RecyclerItemTouchHelperLeft;
import com.example.parsingtask2.model.Post;
import com.example.parsingtask2.networking.VolleyHandler;
import com.example.parsingtask2.networking.VolleyHttp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;


public class MainFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rvPost;
    private PostAdapter postAdapter;
    private final ArrayList<Post> posts = new ArrayList<>();
    private final String TAG = "MainActivity";
    private NavController navController;
    private Bundle arguments;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arguments = getArguments();

        if (arguments != null && arguments.containsKey("post")) {
            post(getNewPost());
        }

        if (arguments != null && arguments.containsKey("postEdited") && arguments.containsKey("postPosition")) {
            edit(getEditedPost(), getEditedPostPosition());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    private void initViews(View view) {
        rvPost = view.findViewById(R.id.rvPost);
        FloatingActionButton btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        navController = Navigation.findNavController(view);


        callBackLeft();

        apiPostList();
    }

    private void callBackLeft() {
        ItemTouchHelper.SimpleCallback simpleCallbackLeft = new RecyclerItemTouchHelperLeft(0, ItemTouchHelper.RIGHT, new RecyclerItemTouchHelperLeft.RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

            }
        });
        new ItemTouchHelper(simpleCallbackLeft).attachToRecyclerView(rvPost);
    }

    private void apiPostList() {
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                //Logger.d(TAG, response);

                Post[] postsComing = new Gson().fromJson(response, Post[].class);

                posts.clear();
                posts.addAll(Arrays.asList(postsComing));

                refreshAdapter(posts);

            }

            @Override
            public void onError(String error) {
                Logger.e(TAG, error);
            }
        });
    }

    private void post(Post post) {
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(post), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                //Logger.d(TAG, response);
                postAdapter.addPost(post);
                Toast.makeText(requireActivity(), "posted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Logger.e(TAG, error);
            }
        });
    }

    private void edit(Post post, int editedPostPosition) {
        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + post.id, VolleyHttp.paramsUpdate(post), new VolleyHandler() {
            @Override
            public void onSuccess(String response) {
                postAdapter.updatePost(editedPostPosition, post);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void refreshAdapter(ArrayList<Post> posts) {
        postAdapter = new PostAdapter(posts, (position) -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("postEdit", posts.get(position));
            bundle.putInt("postPosition", position);
            navController.navigate(R.id.action_mainFragment_to_updateFragment, bundle);
        });
        rvPost.setAdapter(postAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            navController.navigate(R.id.action_mainFragment_to_createFragment);
        }
    }

    private Post getNewPost() {
        return (Post) arguments.getParcelable("post");
    }

    private Post getEditedPost() {
        return (Post) arguments.getParcelable("postEdited");
    }

    private int getEditedPostPosition() {
        return (Integer) arguments.getInt("postPosition");
    }
}