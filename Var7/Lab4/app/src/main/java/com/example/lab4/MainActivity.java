package com.example.lab4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
    }

    public void onClickForward(View view) {
        // Говорим между какими Activity будет происходить связь
        Intent intent = new Intent(this, CustomAlertDialog.class);
        // показываем новое Activity
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(context, data.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
    }
}
