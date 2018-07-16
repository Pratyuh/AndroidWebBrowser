package com.dell.androidwebbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class UrlSearch extends AppCompatActivity implements View.OnClickListener {

    SlidrInterface slidr;

    Button searchurlbtn,homebtn;
    EditText urlinput;
    WebView serchweb;
    ProgressBar pb;
    FrameLayout fl;
    String url;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_search);

        slidr = Slidr.attach(this);




        searchurlbtn = (Button)findViewById(R.id.search);
        homebtn = (Button)findViewById(R.id.homebtn);
        urlinput = (EditText)findViewById(R.id.editText);
        serchweb = (WebView)findViewById(R.id.searchweb);
        pb = (ProgressBar)findViewById(R.id.progressbar);
        fl = (FrameLayout)findViewById(R.id.framelayout);

        pb.setMax(100);

        serchweb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                fl.setVisibility(View.VISIBLE);
                pb.setProgress(newProgress);

                setTitle("Loading..");

                if(newProgress == 100){
                    fl.setVisibility(View.GONE);
                    setTitle(view.getTitle());
                }
                super.onProgressChanged(view,newProgress);
            }
        });



        searchurlbtn.setOnClickListener(this);
        homebtn.setOnClickListener(this);

        url = getIntent().getExtras().get("url_address").toString();
        urlinput.setText(url);

        WebSettings webSettings = serchweb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        serchweb.loadUrl(url);
        serchweb.setWebViewClient(new WebViewClient());
        pb.setProgress(0);



    }

    @Override
    public void onBackPressed() {
        if(serchweb.canGoBack()){
           serchweb.goBack();
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {

        if(v == searchurlbtn){
            SearchWebView();

        }
        if(v == homebtn){
            finish();
            Intent intent = new Intent(UrlSearch.this,MainActivity.class);
            startActivity(intent);
        }

    }

    private void SearchWebView() {



        String url_Address = urlinput.getText().toString();

        if(TextUtils.isEmpty(url_Address)){
            Toast.makeText(UrlSearch.this,"Please Enter Url or Web Address",Toast.LENGTH_LONG).show();
        }
        else{
            String url_withouthttp = url_Address.replace("https://www.","");
            String https = "https://";
            String www = "www.";

            Intent search = new Intent(UrlSearch.this,UrlSearch.class);
            search.putExtra("url_address", https+www+url_withouthttp);
            startActivity(search);

            urlinput.setText("");
            urlinput.requestFocus();
        }



    }



}
