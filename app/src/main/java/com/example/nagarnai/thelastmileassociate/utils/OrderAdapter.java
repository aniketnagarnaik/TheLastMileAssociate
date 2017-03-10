package com.example.nagarnai.thelastmileassociate.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nagarnai.thelastmileassociate.R;
import com.example.nagarnai.thelastmileassociate.model.OrderDetails;

import java.util.List;

/**
 * Created by tarunkg on 3/10/17.
 */

public class OrderAdapter extends ArrayAdapter<OrderDetails> {

    Context context;
    int layoutResourceId;
    List<OrderDetails> orders = null;

    public OrderAdapter(Context context, int layoutResourceId, List<OrderDetails> orders) {
        super(context, layoutResourceId, orders);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        OrderHolder holder = null;
        Log.i("tarunkg", "rendering view");

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new OrderHolder();
            holder.orderId = (TextView)row.findViewById(R.id.orderId);

            row.setTag(holder);
        } else {
            holder = (OrderHolder)row.getTag();
        }

        OrderDetails order = orders.get(position);
        holder.orderId.setText(order.getOrderId());

        return row;
    }

    static class OrderHolder {
        TextView orderId;
    }
}
