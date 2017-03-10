package com.example.nagarnai.thelastmileassociate.event;

import android.util.Log;

import com.example.nagarnai.thelastmileassociate.model.OrderDetails;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by ashruthi on 10/03/17.
 */

public class OrderResultOnMainEvent {

    private static final String TAG = "OrderMain";
    private ArrayList<OrderDetails> orderMembers;

    public OrderResultOnMainEvent(String xmlResponse) {
        startParsing(xmlResponse);
    }

    private void startParsing(String xmlResponse) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(xmlResponse));
            parseXml(xpp);

        } catch (XmlPullParserException e) {
            Log.e(TAG, "XmlPullParserException:" + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException:" + e.getMessage());
        }
    }

    private void parseXml(XmlPullParser parser) throws XmlPullParserException, IOException {

        int eventType = parser.getEventType();

        OrderDetails orderDetails = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = null;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    orderMembers = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("member")) {
                        orderDetails = new OrderDetails();
                    } else if (orderDetails != null) {
                        if (name.equals("state")) {
                            orderDetails.setLatitude(parser.nextText());
                        } else if (name.equals("text")) {
                            orderDetails.setLongitude(parser.nextText());
                        } else if (name.equals("orderId")) {
                            orderDetails.setOrderId(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("member") && orderDetails != null) {

                        Log.d(TAG, "Order being added " + orderDetails.toString());
                        orderMembers.add(orderDetails);
                    }
            }
            eventType = parser.next();
        }


    }

    public ArrayList<OrderDetails> getOrderMembers() {
        return orderMembers;
    }

    public void setOrderMembers(ArrayList<OrderDetails> orderMembers) {
        this.orderMembers = orderMembers;
    }
}
