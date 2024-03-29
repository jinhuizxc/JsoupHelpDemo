package com.example.jsouphelpdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class ShowActivity extends AppCompatActivity {

//    private Toolbar mTbShow;
    private WebView mWvShow;
    private ProgressBar mPbShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);

        String link = getIntent().getStringExtra("link");

//        mTbShow = (Toolbar) findViewById(R.id.tb_show_activity);
        mWvShow = (WebView) findViewById(R.id.wb_show_activity);
        mPbShow = (ProgressBar) findViewById(R.id.pb_show_activity);

//        initAppBar();

        initWebView(link);//初始化WebView

        initWebSettings();

        initWebViewClient();

        initWebChromeClient();


    }

    private void initWebChromeClient() {
        mWvShow.setWebChromeClient(new WebChromeClient(){
            private Bitmap mDefaultVideoPoster;//默认得视频展示图

            @Override
            public void onReceivedTitle(WebView view, final String title) {
                super.onReceivedTitle(view, title);
//                if (mTbShow != null){
//                    mTbShow.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            mTbShow.setTitle(TextUtils.isEmpty(title) ? "加载中..." : title);
//                        }
//                    });
//                }
            }

            @Override
            public Bitmap getDefaultVideoPoster() {
                if (mDefaultVideoPoster == null){
                    mDefaultVideoPoster = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    return mDefaultVideoPoster;
                }
                return super.getDefaultVideoPoster();
            }
        });
    }

    private void initWebViewClient() {
        mWvShow.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mPbShow.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPbShow.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

            }
        });
    }

    private void initWebSettings() {
        WebSettings settings = mWvShow.getSettings();
        //支持获取手势焦点
        mWvShow.requestFocusFromTouch();
        //支持JS
        settings.setJavaScriptEnabled(true);
        //支持插件
        settings.setPluginState(WebSettings.PluginState.ON);
        //设置适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持缩放
        settings.setSupportZoom(true);
        //隐藏原生得缩放控件
        settings.setDisplayZoomControls(false);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.supportMultipleWindows();
        settings.setSupportMultipleWindows(true);
        //设置缓存模式
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(mWvShow.getContext().getCacheDir().getAbsolutePath());

        //设置可访问文件
        settings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true);
        //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19){
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setNeedInitialFocus(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");
    }

    private void initWebView(String url) {
        mWvShow.loadUrl(url);
    }

//    private void initAppBar() {
//        mTbShow.setTitle("载入中...");
//        setSupportActionBar(mTbShow);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}

