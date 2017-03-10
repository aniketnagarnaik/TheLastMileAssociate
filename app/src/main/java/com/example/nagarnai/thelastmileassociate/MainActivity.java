package com.example.nagarnai.thelastmileassociate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button orders_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orders_bt = (Button) findViewById(R.id.orders);
        orders_bt.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.orders:

                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
