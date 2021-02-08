package com.application.rifal.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.application.rifal.myapplication.R;


public class WebViewActivity extends AppCompatActivity {

    String pdfnya;
    WebView pdfviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        pdfviews = findViewById(R.id.pdfviews);

//        String googleDocsUrl = this.getResources().getString(R.string.base_url_pdf)+ pdf;
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.parse(googleDocsUrl ), "application/pdf");
//        this.startActivity(intent);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("urlnya")!= null)
        {
            pdfnya = bundle.getString("urlnya");
        }


        pdfviews.getSettings().setLoadWithOverviewMode(true);
        pdfviews.getSettings().setUseWideViewPort(true);


        pdfviews.getSettings().setBuiltInZoomControls(true);
        pdfviews.getSettings().setSupportZoom(true);

        pdfviews.getSettings().setJavaScriptEnabled(true);
        pdfviews.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        pdfviews.setWebViewClient(new WebViewClient());
        pdfviews.loadUrl(pdfnya);
        pdfviews.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);
                findViewById(R.id.pls).setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.progressBar1).setVisibility(View.GONE);
                findViewById(R.id.pls).setVisibility(View.GONE);

                findViewById(R.id.pdfviews).setVisibility(View.VISIBLE);
            }
        });
    }


}
