package com.example.ebayprojet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity implements itemAdapter.OnItemClickListener {
    public static final String extraTitle = "extratitle";
    public static final String extraUrl = "extraurl";
    public static final String extraId = "extraid";
    public static final String extraPrice = "extraprice";
    public static final String extraShippingPrice = "extrashippingprice";
    public static final String extrahandlingTime = "extrahandlingTime";
    public static final String extraoneDayShippingAvailable = "extraoneDayShippingAvailable";
    public static final String extrashippingType = "extrashippingType";
    public static final String extrashippingFrom = "extrashippingFrom";
    public static final String extrashipToLocation = "extrashipToLocation";
    public static final String extraexpeditedShipping = "extraexpeditedShipping";

    private SwipeRefreshLayout swipeContainer;

    private ProgressBar spinner;
    private TextView loading;
    private TextView totalResult;
    private RecyclerView recyclerView;
    private RequestQueue mRequestQueue;
    private itemAdapter itemadapter;
    private TextView norecords;
    private ArrayList<ItemClass> itemArray;


    private String ResultNum = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        spinner = findViewById(R.id.progressBar1);
        loading = findViewById(R.id.textView);
        totalResult = findViewById(R.id.textView8);
        recyclerView = findViewById(R.id.recyclerView);
        norecords = findViewById(R.id.NoRecords);
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemArray = new ArrayList<>();
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
                httpRequests();
                swipeContainer.setRefreshing(false);
            }
        });
        swipeContainer.setColorSchemeColors(
                getResources().getColor(android.R.color.darker_gray),
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );
        itemArray = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        httpRequests();
    }

    private void httpRequests(){
        String url = constructUrl();

        JsonObjectRequest getJsonRequest;

        getJsonRequest = new JsonObjectRequest (Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJson(response);
                        settingUI();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error Message : ", String.valueOf(error));
            }
        });

        mRequestQueue.add(getJsonRequest);
    }

    private void settingUI(){
        final SpannableStringBuilder sb = new SpannableStringBuilder("Showing " + ResultNum + " results for " + MainActivity.getKeywords);
        final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(46, 103, 247));
        final ForegroundColorSpan fcs2 = new ForegroundColorSpan(Color.rgb(46, 103, 247));
        sb.setSpan(fcs2, 8, 8 + ResultNum.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(fcs, 21 + ResultNum.length(), 21 + ResultNum.length() + MainActivity.getKeywords.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        totalResult.setText(sb);

        itemadapter = new itemAdapter(this, itemArray);
        RecyclerView.LayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(itemadapter);

        itemadapter.setOnItemClickListener(SearchResult.this);

        spinner.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        if(itemArray.size() > 0)
            totalResult.setVisibility(View.VISIBLE);
        else{
            norecords.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "No Records", Toast.LENGTH_SHORT).show();
        }
    }

    private void parseJson(JSONObject response){
        try {
            ResultNum = response.getJSONArray("findItemsAdvancedResponse").getJSONObject(0)
                    .getJSONArray("paginationOutput").getJSONObject(0).getJSONArray("totalEntries")
                    .getString(0).toString();
            JSONArray item = response.getJSONArray("findItemsAdvancedResponse").getJSONObject(0)
                    .getJSONArray("searchResult").getJSONObject(0)
                    .getJSONArray("item");

            for(int i = 0; i < item.length(); i++){
                String handlingTime;
                String oneDayShippingAvailable;
                String shippingType;
                String shippingFrom;
                String shipToLocation;
                String expeditedShipping;
                try {
                    handlingTime = item.getJSONObject(i).getJSONArray("shippingInfo").getJSONObject(0).getJSONArray("handlingTime").getString(0);
                }catch(Exception e){
                    handlingTime = "";
                }
                try {
                    oneDayShippingAvailable = item.getJSONObject(i).getJSONArray("shippingInfo").getJSONObject(0).getJSONArray("oneDayShippingAvailable").getString(0);
                }catch(Exception e){
                    oneDayShippingAvailable = "";
                }
                try {
                    shippingType = item.getJSONObject(i).getJSONArray("shippingInfo").getJSONObject(0).getJSONArray("shippingType").getString(0);
                }catch(Exception e){
                    shippingType = "";
                }
                try {
                    shippingFrom = item.getJSONObject(i).getJSONArray("location").getString(0);
                }catch(Exception e){
                    shippingFrom = "";
                }
                try {
                    shipToLocation = item.getJSONObject(i).getJSONArray("shippingInfo").getJSONObject(0).getJSONArray("shipToLocations").getString(0);
                }catch(Exception e){
                    shipToLocation = "";
                }
                try {
                    expeditedShipping = item.getJSONObject(i).getJSONArray("shippingInfo").getJSONObject(0).getJSONArray("expeditedShipping").getString(0);
                }catch(Exception e){
                    expeditedShipping = "";
                }
                String condition;
                try {
                    condition = item.getJSONObject(i).getJSONArray("condition").getJSONObject(0)
                            .getJSONArray("conditionDisplayName").getString(0);
                }catch (Exception e){ condition = "N/A"; }
                try {
                    String imageUrl = item.getJSONObject(i).getJSONArray("galleryURL").getString(0);
                    String title = item.getJSONObject(i).getJSONArray("title").getString(0);
                    String shippingPrice = item.getJSONObject(i).getJSONArray("shippingInfo").getJSONObject(0)
                            .getJSONArray("shippingServiceCost").getJSONObject(0).getString("__value__");
                    String price = item.getJSONObject(i).getJSONArray("sellingStatus").getJSONObject(0)
                            .getJSONArray("convertedCurrentPrice").getJSONObject(0).getString("__value__");
                    String topRated = item.getJSONObject(i).getJSONArray("topRatedListing").getString(0);
                    String itemUrl = item.getJSONObject(i).getJSONArray("viewItemURL").getString(0);
                    String itemId = item.getJSONObject(i).getJSONArray("itemId").getString(0);

                    itemArray.add(new ItemClass(imageUrl, title, shippingPrice, condition, price, topRated, itemUrl, itemId
                            , handlingTime, oneDayShippingAvailable, shippingType, shippingFrom, shipToLocation, expeditedShipping));
                }catch (Exception e){ continue; }
                if(itemArray.size() >= 50)
                    break;
            }
        } catch (JSONException e) {
            Log.e("Volley Error Message : ", String.valueOf(e));
            ResultNum = "0";
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ItemClass clickedItem = itemArray.get(position);

        detailIntent.putExtra(extraTitle, clickedItem.gettitle());
        detailIntent.putExtra(extraUrl, clickedItem.getitemUrl());
        detailIntent.putExtra(extraId, clickedItem.getmitemId());
        detailIntent.putExtra(extraPrice, clickedItem.getprice());
        detailIntent.putExtra(extraShippingPrice, clickedItem.getshippingPrice());
        detailIntent.putExtra(extrahandlingTime, clickedItem.gethandlingTime());
        detailIntent.putExtra(extraoneDayShippingAvailable, clickedItem.getoneDayShippingAvailable());
        detailIntent.putExtra(extrashippingType, clickedItem.getshippingType());
        detailIntent.putExtra(extrashippingFrom, clickedItem.getshippingFrom());
        detailIntent.putExtra(extrashipToLocation, clickedItem.getshipToLocation());
        detailIntent.putExtra(extraexpeditedShipping, clickedItem.getexpeditedShipping());

        startActivity(detailIntent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    private String constructUrl(){
        String url = "https://ebay-projects-190.wl.r.appspot.com/search?keywords=";

        url += MainActivity.getKeywords;
        url += "&PRFrom=";
        url += MainActivity.getPRFrom;
        url += "&PRTo=";
        url += MainActivity.getPRTo;
        if(MainActivity.getNew)
            url += "&New_=on";
        if(MainActivity.getUsed)
            url += "&Used_=on";
        if(MainActivity.getUnspecified)
            url += "&Unspecified_=on";
        if(MainActivity.getSortby==0){
            url += "&sortby=BestMatch";
        }
        else if(MainActivity.getSortby==1){
            url += "&sortby=CurrentPriceHighest";
        }
        else if(MainActivity.getSortby==2){
            url += "&sortby=PricePlusShippingHighest";
        }
        else if(MainActivity.getSortby==3){
            url += "&sortby=PricePlusShippingLowest";
        }

        return url;
    }
}
