package com.example.parsingtask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.parsingtask2.adapter.PostAdapter;
import com.example.parsingtask2.model.Post;
import com.example.parsingtask2.networking.VolleyHandler;
import com.example.parsingtask2.networking.VolleyHttp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}