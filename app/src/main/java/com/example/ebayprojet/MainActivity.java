package com.example.ebayprojet;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String[] spinnerArray = {"Best Match", "Price: highest first",
            "Price + Shipping: Highest first", "Price + Shipping: Lowest first"};

    public static String getKeywords;
    public static String getPRFrom;
    public static String getPRTo;
    public static boolean getNew;
    public static boolean getUsed;
    public static boolean getUnspecified;
    public static int getSortby;

    TextView warning1;
    TextView warning2;
    Spinner spinner;
    CheckBox New_checkbox;
    CheckBox Used_checkbox;
    CheckBox Unspecified_checkbox;
    EditText MinPrice;
    EditText MaxPrice;
    EditText keyword;
    boolean SearchError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        warning1 = findViewById(R.id.textView6);
        warning2 = findViewById(R.id.textView7);
        spinner = findViewById(R.id.spinner);
        New_checkbox = findViewById(R.id.checkBox);
        Used_checkbox = findViewById(R.id.checkBox2);
        Unspecified_checkbox = findViewById(R.id.checkBox3);
        MinPrice = findViewById(R.id.textInputEditText2);
        MaxPrice = findViewById(R.id.textInputEditText);
        keyword = findViewById(R.id.textInputEditText3);
        SearchError = false;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button SearchButton = findViewById(R.id.button);

        Button ClearButton = findViewById(R.id.button2);
    }

    public void onSearchClicked(View view){
        SearchError = false;

        warning1.setVisibility(View.GONE);
        warning2.setVisibility(View.GONE);

        int sortBy = spinner.getSelectedItemPosition();
        boolean New_ = New_checkbox.isChecked();
        boolean Used_ = Used_checkbox.isChecked();
        boolean Unspecified_ = Unspecified_checkbox.isChecked();
        String PRFrom_ = MinPrice.getText().toString();
        String PRTo_ = MaxPrice.getText().toString();
        String keywords = keyword.getText().toString();
        float PRFrom = -1;
        float PRTo = -1;
        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();
        CharSequence toastError = "Please fix all fields with errors";

        if(keywords.trim().isEmpty() || keywords.length() == 0){
            warning1.setVisibility(View.VISIBLE);
            SearchError = true;
            Toast.makeText(context, toastError, duration).show();
        }
        if(!PRFrom_.trim().isEmpty()){
            PRFrom = Float.parseFloat(PRFrom_);
        }
        if(!PRTo_.trim().isEmpty()){
            PRTo = Float.parseFloat(PRTo_);
        }
        if(PRFrom != -1 && PRTo != -1){
            if(PRFrom > PRTo){
                warning2.setVisibility(View.VISIBLE);
                SearchError = true;
                Toast.makeText(context, toastError, duration).show();
            }
        }

        if(!SearchError){
            getKeywords = keywords;
            getPRFrom = PRFrom_;
            getPRTo = PRTo_;
            getNew = New_;
            getUsed = Used_;
            getUnspecified = Unspecified_;
            getSortby = sortBy;
            showResult();
        }

    }

    public void onClearClicked(View view){
        TextView warning1 = findViewById(R.id.textView6);
        TextView warning2 = findViewById(R.id.textView7);
        warning1.setVisibility(View.GONE);
        warning2.setVisibility(View.GONE);

        spinner.setSelection(0);
        if(New_checkbox.isChecked())
            New_checkbox.toggle();
        if(Used_checkbox.isChecked())
            Used_checkbox.toggle();
        if(Unspecified_checkbox.isChecked())
            Unspecified_checkbox.toggle();
        MinPrice.getText().clear();
        MaxPrice.getText().clear();
        keyword.getText().clear();
    }

    public void showResult(){
        Intent intent = new Intent(this, SearchResult.class);
        startActivity(intent);
    }
}


