package com.example.ebayprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.ebayprojet.SearchResult.extraId;
import static com.example.ebayprojet.SearchResult.extraPrice;
import static com.example.ebayprojet.SearchResult.extraShippingPrice;
import static com.example.ebayprojet.SearchResult.extraTitle;
import static com.example.ebayprojet.SearchResult.extraUrl;
import static com.example.ebayprojet.SearchResult.extraexpeditedShipping;
import static com.example.ebayprojet.SearchResult.extrahandlingTime;
import static com.example.ebayprojet.SearchResult.extraoneDayShippingAvailable;
import static com.example.ebayprojet.SearchResult.extrashipToLocation;
import static com.example.ebayprojet.SearchResult.extrashippingFrom;
import static com.example.ebayprojet.SearchResult.extrashippingType;

public class DetailActivity extends AppCompatActivity {

    private RequestQueue detailRequestQueue;
    public static ArrayList<String> imgGallery;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ProgressBar spinner;
    private TextView loading;

    private String itemUrl;
    private String itemid;
    public static String title;
    public static String price;
    public static String shippingPrice;
    public static String handlingTime;
    public static String oneDayShippingAvailable;
    public static String shippingType;
    public static String shippingFrom;
    public static String shipToLocation;
    public static String expeditedShipping;

    public static String userId;
    public static String feedbackRatingStar;
    public static String feedbackScore;
    public static String positiveFeedbackPercent;
    public static String topRatedSeller;
    public static String returnsWidthin;
    public static String returnsAccepted;
    public static String shippingCostPaidBy;
    public static String internationalRetrnsAccepted;
    public static String refund;
    public static String internationalRefund;
    public static String internationalReturnsWithin;
    public static String internationalShippingCostPaidBy;
    public static String Description;
    public static String subtitle;
    public static ArrayList<String> specName;
    public static ArrayList<String> specValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imgGallery = new ArrayList<>();
        spinner = findViewById(R.id.progress1);
        loading = findViewById(R.id.progress2);
        specName = new ArrayList<>();
        specValue = new ArrayList<>();

        Intent intent = getIntent();
        title = intent.getStringExtra(extraTitle);
        itemUrl = intent.getStringExtra(extraUrl);
        itemid = intent.getStringExtra(extraId);
        price = intent.getStringExtra(extraPrice);
        shippingPrice = intent.getStringExtra(extraShippingPrice);
        handlingTime = intent.getStringExtra(extrahandlingTime);
        oneDayShippingAvailable = intent.getStringExtra(extraoneDayShippingAvailable);
        shippingType = intent.getStringExtra(extrashippingType);
        shippingFrom = intent.getStringExtra(extrashippingFrom);
        shipToLocation = intent.getStringExtra(extrashipToLocation);
        expeditedShipping = intent.getStringExtra(extraexpeditedShipping);

        setTitle(title);


        detailRequestQueue = Volley.newRequestQueue(this);
        detailHttpRequest();
    }

    private ViewPagerAdapter createCardAdapter() {
        return new ViewPagerAdapter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if(item.getItemId() == R.id.redirect){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemUrl));
            startActivity(browserIntent);
            return true;
        }
        return false;
    }



    private void detailHttpRequest(){
        String url = "https://ebay-projects-190.wl.r.appspot.com/detail?id=" + itemid;

        JsonObjectRequest getJsonRequest;

        getJsonRequest = new JsonObjectRequest (Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJson(response);
                        setupCardFragment();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error Message : ", String.valueOf(error));
            }
        });

        detailRequestQueue.add(getJsonRequest);
    }

    private void parseJson(JSONObject response){
        try {
            JSONArray imgs = response.getJSONObject("Item").getJSONArray("PictureURL");
            for(int i = 0; i < imgs.length(); i++){
                imgGallery.add(imgs.getString(i));
            }
        }catch (Exception e){}
        try {
            userId = response.getJSONObject("Item").getJSONObject("Seller").getString("UserID");
        }catch (Exception e){userId = "";}
        try {
            feedbackRatingStar = response.getJSONObject("Item").getJSONObject("Seller").getString("FeedbackRatingStar");
        }catch (Exception e){feedbackRatingStar = "";}
        try {
            feedbackScore = response.getJSONObject("Item").getJSONObject("Seller").getString("FeedbackScore");
        }catch (Exception e){feedbackScore = "";}
        try {
            positiveFeedbackPercent = response.getJSONObject("Item").getJSONObject("Seller").getString("PositiveFeedbackPercent");
        }catch (Exception e){positiveFeedbackPercent = "";}
        try {
            topRatedSeller = response.getJSONObject("Item").getJSONObject("Seller").getString("TopRatedSeller");
        }catch (Exception e){topRatedSeller = "";}
        try {
            returnsWidthin = response.getJSONObject("Item").getJSONObject("ReturnPolicy").getString("ReturnsWithin");
        }catch (Exception e){returnsWidthin = "";}
        try {
            returnsAccepted = response.getJSONObject("Item").getJSONObject("ReturnPolicy").getString("ReturnsAccepted");
        }catch (Exception e){returnsAccepted = "";}
        try {
            shippingCostPaidBy = response.getJSONObject("Item").getJSONObject("ReturnPolicy").getString("ShippingCostPaidBy");
        }catch (Exception e){shippingCostPaidBy = "";}
        try {
            internationalRetrnsAccepted = response.getJSONObject("Item").getJSONObject("ReturnPolicy").getString("InternationalReturnsAccepted");
        }catch (Exception e){internationalRetrnsAccepted = "";}
        try {
            refund = response.getJSONObject("Item").getJSONObject("ReturnPolicy").getString("Refund");
        }catch (Exception e){refund = "";}
        try {
            internationalRefund = response.getJSONObject("Item").getJSONObject("ReturnPolicy").getString("InternationalRefund");
        }catch (Exception e){internationalRefund = "";}
        try {
            internationalReturnsWithin = response.getJSONObject("Item").getJSONObject("ReturnPolicy").getString("InternationalReturnsWithin");
        }catch (Exception e){internationalReturnsWithin = "";}
        try {
            internationalShippingCostPaidBy = response.getJSONObject("Item").getJSONObject("ReturnPolicy").getString("InternationalShippingCostPaidBy");
        }catch (Exception e){internationalShippingCostPaidBy = "";}
        try {
            Description = response.getJSONObject("Item").getJSONObject("ReturnPolicy").getString("Description");
        }catch (Exception e){Description = "";}
        try{
            subtitle = response.getJSONObject("Item").getString("Subtitle");
        }catch (Exception e){subtitle = "";}
        try{
            JSONArray array = response.getJSONObject("Item").getJSONObject("ItemSpecifics").getJSONArray("NameValueList");
            for(int i = 0; i < array.length(); i++){
                String tempName = array.getJSONObject(i).getString("Name");
                String tempValue = array.getJSONObject(i).getJSONArray("Value").getString(0);
                specName.add(tempName);
                specValue.add(tempValue);
            }
        }catch (Exception e){}
    }

    private void setupCardFragment(){
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager.setAdapter(createCardAdapter());
        //viewPager.setUserInputEnabled(false);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if(position == 0) {
                            tab.setIcon(R.drawable.information_variant_selected);
                            tab.setText("PRODUCT");
                        }
                        else if(position == 1){
                            tab.setIcon(R.drawable.ic_seller);
                            tab.setText("SELLER INFO");
                        }
                        else if(position == 2){
                            tab.setIcon(R.drawable.truck_delivery_selected);
                            tab.setText("SHIPPING");
                        }
                    }
                }).attach();
        spinner.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
    }
}
