package com.maddy.learning;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {


    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebClient webClient = new WebClient(this);
        webView.setWebViewClient(webClient);
        webView.loadUrl("https://bødekontrol.dk");

    }

    private class WebClient extends WebViewClient
    {
        private Context context;

        public WebClient(Context mainActivity) {
            this.context = mainActivity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            String cookies = CookieManager.getInstance().getCookie(url);
            Log.d("Cookies", "All the cookies in a string:" + cookies);
        }
    }




}
