package stack.com.stackapi.ui;


import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import stack.com.stackapi.R;
import stack.com.stackapi.utils.AppConstants;

public class LoginActivity extends Activity {

    private static String CLIENT_ID = "6654";
    //Use your own client secret
    private static String REDIRECT_URI="https://stackexchange.com/oauth/login_success";
    private static String OAUTH_URL ="https://stackexchange.com/oauth/dialog";
    WebView web;
    Button auth;
    SharedPreferences pref;
    TextView Access;
    private ProgressWheel progressWheel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_dialog);
        progressWheel = (ProgressWheel)findViewById(R.id.progress_wheel);
        progressWheel.stopSpinning();
        if(AppConstants.isInternetAvailable(this)) {
        web = (WebView)findViewById(R.id.webv);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(OAUTH_URL+"?redirect_uri="+REDIRECT_URI+"&client_id="+CLIENT_ID);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view, url, favicon);
                progressWheel.spin();
            }
            String authCode;
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("access_token=")) {
                 getAccessToken(url);
                 String strAccessToken=AppConstants.readPreferences(LoginActivity.this,"AcessToken","0");
                    Log.d("Acess Token",strAccessToken);
                 if(!strAccessToken.equals("0")) {
                     Toast.makeText(getApplicationContext(),"Authenticated Successfully", Toast.LENGTH_SHORT).show();
                     progressWheel.stopSpinning();
                     Intent intent= new Intent(LoginActivity.this,StackBaseActivity.class);
                     intent.putExtra("Profile","Create");
                     startActivity(intent);
                     finish();
                 }
                }else if(url.contains("error=access_denied")){
                    Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                    progressWheel.stopSpinning();
                }else{
                    progressWheel.stopSpinning();
                }
            }
        });
        }else{
            Toast.makeText(this, getString(R.string.internet), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Return the access token
     * @param url
     */
    private void getAccessToken(String url) {
        int i=0;
        final String[] split = url.split("#|&|=");
        for (String values : split) {
            if(i==2){
                AppConstants.savePreferences(this,"AcessToken",values);
            }
            i++;
        }
    }

}