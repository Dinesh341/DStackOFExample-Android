/**
 * @category DineshSample
 * @author Dinesh <dineshbabu341@gmail.com>
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
package stack.com.stackapi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * AppConstants.java
 * <p/>
 * Description: This is common class for storing all constants needed for the app
 */
public final class AppConstants {


    public static final String APP_BASE_URL = "https://api.stackexchange.com/2.2";
    public static final class CommonAPI {
         public static final String KEY = "key";
        public static final String KEYVALUE = "FPjJhcm3TucOayY0NFjwpA((";
        public static final String ACCESS_TOKEN = "access_token";
        public static final String ORDER = "order";
        public static final String SORT = "sort";
        public static final String HOT = "hot";
        public static final String SITE = "site";
        public static final String STACKOVERFLOW = "stackoverflow";
        public static final String DESC = "desc";
        public static final String ACTIVE = "activity";
        public static final String MONTH = "month";
        public static final String CREATION = "creation";
        public static final String VOTES = "votes";
        public static final String WEEK = "week";

        private CommonAPI() {
            //need empty constructor
        }

    }

    /**
     * Return the date format from the timestamp
     * @param timeStamp
     * @return
     */
    public static String getDate(long timeStamp){
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date currenTimeZone=new java.util.Date(timeStamp*1000);
            return sdf.format(currenTimeZone);
        }
        catch(Exception ex){
            return "xx";
        }
    }

    /**
     * Returns the Internet Connection Available Status
     *
     * @param context - Context environment passed by this parameter
     * @return true if the Internet Connection is Available and false otherwise
     */
    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }
    // Save the value in preference.
    public static void savePreferences(Context activity, String key,
                                       String value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // Read the value in prefrence
    public static String readPreferences(Context activity, String key,
                                         String defaultValue) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(activity.getApplicationContext());
        return sp.getString(key, defaultValue);
    }
}
