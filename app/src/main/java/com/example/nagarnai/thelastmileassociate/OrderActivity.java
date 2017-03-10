package com.example.nagarnai.thelastmileassociate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.nagarnai.thelastmileassociate.event.OrderResultOnMainEvent;
import com.example.nagarnai.thelastmileassociate.model.OrderDetails;
import com.example.nagarnai.thelastmileassociate.utils.JSONParser;
import com.example.nagarnai.thelastmileassociate.utils.OrderAdapter;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

        activity = this;

    }

    @Override
    protected void onResume() {
        super.onResume();


        //final String url = "http://ez-hackathon-zeroprint.integ.amazon.com/unscheduledOrders";
//        final String url = "http://ez-hackathon-zeroprint.integ.amazon.com/getActiveOrders?dId=";
        final String url = "http://ez-hackathon-zeroprint.integ.amazon.com/getActiveOrders";
        new AsyncTask<String, String, String>(){

            @Override
            protected void onPostExecute(String httpResponse) {

                // UI elements
                super.onPostExecute(httpResponse);
                Log.d(httpResponse,"Success");

                OrderDetails oD1 = new OrderDetails("101","13.071028","77.567969");
                OrderDetails od2 = new OrderDetails("102","13.071613","77.563356");
                List<OrderDetails> orders = new ArrayList<OrderDetails>();
                orders.add(oD1);
                orders.add(od2);

                setContentView(R.layout.orderdetails);

            }

            @Override
            protected String doInBackground(String... strings) {

                String xmlString = "<GetActiveOrdersRequest><dId>" + strings[0] + "</dId></GetActiveOrdersRequest>";
//                String xmlString = strings[0];
                Log.d(TAG, xmlString);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml"), xmlString);
                try {
                    Log.d(TAG, "" + requestBody.contentLength());
                } catch (IOException e) {
                    Log.e(TAG, "" + e.getMessage());
                }
                Request request = new Request.Builder()
                        .put(requestBody)
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
