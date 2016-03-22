package stack.com.stackapi.ui;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import stack.com.stackapi.R;
import stack.com.stackapi.utils.AppConstants;

public class InitialActivity extends Activity implements View.OnClickListener {

    private static String CLIENT_ID = "6654";
    //Use your own client secret
    private static String REDIRECT_URI="https://stackexchange.com/oauth/login_success";
    private static String GRANT_TYPE="authorization_code";
    private static String TOKEN_URL ="https://accounts.google.com/o/oauth2/token";
    private static String OAUTH_URL ="https://stackexchange.com/oauth/dialog";
    private static String OAUTH_SCOPE="https://www.googleapis.com/auth/urlshortener";
    //Change the Scope as you need
  //  https://api.stackexchange.com/2.2/me/associated?access_token=NyTFaaY6VliOZIZ17vxJKw%29%29&key=FPjJhcm3TucOayY0NFjwpA%28%28
    //https://www.learn2crack.com/2014/01/android-oauth2-webview.html
    WebView web;
    Button auth;
    SharedPreferences pref;
    TextView Access;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.login_activtity);
     Button btLogin=(Button)findViewById(R.id.login);
        btLogin.setOnClickListener(this);
        TextView tvWithOutAccount=(TextView)findViewById(R.id.without_login);
        tvWithOutAccount.setOnClickListener(this);
        String strAccessToken= AppConstants.readPreferences(this, "AcessToken", "0");
         if(!strAccessToken.equals("0")){
             Intent intent= new Intent(this,StackBaseActivity.class);
             intent.putExtra("Profile","Create");
             startActivity(intent);
         }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                Intent webView= new Intent(this,LoginActivity.class);
                startActivity(webView);
                break;
            case R.id.without_login:
                Intent intent= new Intent(this,StackBaseActivity.class);
                intent.putExtra("Profile","empty");
                startActivity(intent);
                break;
            default:
        }
    }
}