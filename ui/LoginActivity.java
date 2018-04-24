package com.stardust.guangzhou.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.stardust.guangzhou.App;
import com.stardust.guangzhou.R;

import java.net.CookieHandler;

/**
 * Created by Stardust on 2017/4/15.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    public static final int RESULT_CODE_FILE_CHOOSE = 33423;
    private WebView mWebView;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mFilePathCallback;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setInitialScale(250);
        WebSettings settings = mWebView.getSettings();
        settings.setAllowFileAccess(true);
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return shouldOverrideUrlLoading(request.getUrl().toString());

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return shouldOverrideUrlLoading(url);
            }

            public boolean shouldOverrideUrlLoading(String url) {
                Log.i(TAG, url);
                if (url.endsWith("/detail")) {
                    App.getClient().setLogined(true);
                    finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String cookie = CookieManager.getInstance().getCookie(url);
                Log.i(TAG, "cookie: " + cookie);
            }


        });
        mWebView.setWebChromeClient(new FileChooseWebClient(this));

        mWebView.loadUrl(App.getClient().getBaseUrl() + "login");
    }

    @Override
    public void finish() {
        App.getClient().refreshUserInfo();
        super.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == RESULT_CODE_FILE_CHOOSE) {
            Uri result = intent == null || resultCode != Activity.RESULT_OK ? null
                    : intent.getData();
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            } else {
                mFilePathCallback.onReceiveValue(new Uri[]{result});
            }
        }
    }

    private class FileChooseWebClient extends WebChromeClient {
        private Activity mActivity;

        public FileChooseWebClient(Activity activity) {
            mActivity = activity;
        }

        //The undocumented magic method override
        //Eclipse will swear at you if you try to put @Override here
        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {

            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            mActivity.startActivityForResult(Intent.createChooser(i, "File Chooser"), RESULT_CODE_FILE_CHOOSE);
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            mActivity.startActivityForResult(
                    Intent.createChooser(i, "File Browser"),
                    RESULT_CODE_FILE_CHOOSE);
        }

        //For Android 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            mActivity.startActivityForResult(Intent.createChooser(i, "File Chooser"), RESULT_CODE_FILE_CHOOSE);

        }

        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                         FileChooserParams fileChooserParams) {
            mFilePathCallback = filePathCallback;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            mActivity.startActivityForResult(Intent.createChooser(i, "File Chooser"), RESULT_CODE_FILE_CHOOSE);
            return true;
        }
    }
}
