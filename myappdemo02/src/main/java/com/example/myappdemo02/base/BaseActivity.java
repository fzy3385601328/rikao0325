package com.example.myappdemo02.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutById());
        initView(savedInstanceState);
    }

    protected abstract int getLayoutById();

    protected abstract void initView(Bundle savedInstanceState);
}
