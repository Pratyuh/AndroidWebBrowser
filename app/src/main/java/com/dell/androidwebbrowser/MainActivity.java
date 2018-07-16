package com.dell.androidwebbrowser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button searchbtnhome;
    EditText inputurl;
    Button facebook,youtube,amazon,flipkart,yahoo,google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);



        searchbtnhome = (Button)findViewById(R.id.search_btn);
        inputurl = (EditText)findViewById(R.id.searchurl);
        facebook = (Button)findViewById(R.id.button3);
         youtube = (Button)findViewById(R.id.button4);
        amazon = (Button)findViewById(R.id.button5);
        flipkart = (Button)findViewById(R.id.button6);
        yahoo = (Button)findViewById(R.id.button7);
        google = (Button)findViewById(R.id.button8);





        searchbtnhome.setOnClickListener(this);
       facebook.setOnClickListener(this);
       youtube.setOnClickListener(this);
       amazon.setOnClickListener(this);
        flipkart.setOnClickListener(this);
        yahoo.setOnClickListener(this);
       google.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
      if(v == searchbtnhome){
         OpenWebsite();

          //hide keyboard
          InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
          inputMethodManager.hideSoftInputFromWindow(inputurl.getWindowToken(),0);

      }
        if(v == facebook){
            Intent openfb = new Intent(MainActivity.this,UrlSearch.class);
            openfb.putExtra("url_address","https://www.facebook.com");
            startActivity(openfb);
        }
        if(v == youtube){
            Intent openyoutube = new Intent(MainActivity.this,UrlSearch.class);
            openyoutube.putExtra("url_address","https://www.youtube.com");
            startActivity(openyoutube);
        }
        if(v == amazon){
            Intent openamazon = new Intent(MainActivity.this,UrlSearch.class);
            openamazon.putExtra("url_address","https://www.amazon.com");
            startActivity(openamazon);
        }
        if(v == flipkart){
            Intent intent = new Intent(MainActivity.this,UrlSearch.class);
            intent.putExtra("url_address","https://www.flipkart.com");
            startActivity(intent);
        }
        if(v == yahoo){
            Intent intent = new Intent(MainActivity.this,UrlSearch.class);
            intent.putExtra("url_address","https://www.yahoo.com");
            startActivity(intent);
        }
        if(v == google){
            Intent intent = new Intent(MainActivity.this,UrlSearch.class);
            intent.putExtra("url_address","https://www.google.com");
            startActivity(intent);
        }


    }

    private void OpenWebsite() {



        String url_Address = inputurl.getText().toString();

        if(TextUtils.isEmpty(url_Address)){
            Toast.makeText(MainActivity.this,"Please Enter Url or Web Address",Toast.LENGTH_LONG).show();
        }
        else{
            String url_withouthttp = url_Address.replace("https://www.","");
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(MainActivity.this,UrlSearch.class);
            search.putExtra("url_address", https+www+url_withouthttp);
            startActivity(search);

            inputurl.setText("");
            inputurl.requestFocus();
        }



    }



}
