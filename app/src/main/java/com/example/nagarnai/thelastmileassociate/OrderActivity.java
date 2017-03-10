package com.example.nagarnai.thelastmileassociate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.nagarnai.thelastmileassociate.model.OrderDetails;
import com.example.nagarnai.thelastmileassociate.utils.OrderAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ashruthi on 09/03/17.
 */

public class OrderActivity extends AppCompatActivity {

    private static final String TAG = "OrderActivity";
    private ListView listView;
    OkHttpClient client;
    private AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderdetails);


        client = new OkHttpClient();

        listView = (ListView)findViewById(R.id.orders_lv);


    }

    @Override
    protected void onResume() {
        super.onResume();

        final String url = "http://ez-hackathon-zeroprint.integ.amazon.com/unscheduledOrders";
        //final String url = "http://h-c3-1a-a1dafa6a.eu-west-1.amazon.com:8000/getActiveOrders?dId=";
        new AsyncTask<String, String, String>(){

            @Override
            protected void onPostExecute(String httpResponse) {

                // UI elements
                super.onPostExecute(httpResponse);
                Log.d(httpResponse,"Success");
                List<OrderDetails> orders = new ArrayList<OrderDetails>();
                OrderAdapter orderAdapter = new OrderAdapter(activity, R.id.order, orders);
                listView.setAdapter(orderAdapter);
                listView.invalidate();
            }

            @Override
            protected String doInBackground(String... strings) {

                // HTTP call
                Request request = new Request.Builder()
                        .get()
                        .url(url)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    Log.d("Response", response.toString());
                    String strResponse = response.body().string();
                    Log.d(TAG, "Got response" + strResponse);

                } catch (IOException e) {
                    Log.e(TAG, "response: " + response);
                }

                // Return response . to text
                return response.toString();
            }
        }.execute("1");
        Log.d(TAG, "building request with url" + url);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {

        super.onStop();
    }

}
