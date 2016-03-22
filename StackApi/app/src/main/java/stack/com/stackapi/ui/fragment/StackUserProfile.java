package stack.com.stackapi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import model.profile.Example;
import model.profile.Item;
import model.question.QuestionStackResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import stack.com.stackapi.R;
import stack.com.stackapi.adapter.QuestionListAdapter;
import stack.com.stackapi.api.RetrofitRequestHelper;
import stack.com.stackapi.utils.AppConstants;

/**
 * Created by DineshBabuG on 3/8/2016.
 */
public class StackUserProfile extends Fragment{
    private ProgressWheel progressWheel;
    private RecyclerView voteRecyler;
    private QuestionListAdapter postListAdapter;
    private TextView  tvBadgeCount,tvReputation,tvQuestionCount,tvAccountId,tvCreation,tvUserId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressWheel = (ProgressWheel)view.findViewById(R.id.progress_wheel);
        progressWheel.stopSpinning();
         tvBadgeCount=(TextView)view.findViewById(R.id.badgecounts);
         tvReputation=(TextView)view.findViewById(R.id.reputation_values);
         tvQuestionCount=(TextView)view.findViewById(R.id.questioncount);
         tvAccountId=(TextView)view.findViewById(R.id.account_id_values);
         tvCreation=(TextView)view.findViewById(R.id.creation_values);
         tvUserId=(TextView)view.findViewById(R.id.user_id);
        if(AppConstants.isInternetAvailable(getActivity())) {
            getUserProfile();
        }else{
            Toast.makeText(getActivity(), getString(R.string.internet), Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Get the Question based on the User Votes
     */
    private void getUserProfile() {
        progressWheel.spin();
        String strAccessToken=AppConstants.readPreferences(getActivity(),"AcessToken","0");
        HashMap<String,String> userCredentials=new HashMap<>();
        userCredentials.put(AppConstants.CommonAPI.ACCESS_TOKEN,strAccessToken);
        userCredentials.put(AppConstants.CommonAPI.KEY, AppConstants.CommonAPI.KEYVALUE);
        new RetrofitRequestHelper().getInstance().getUserProfile(userCredentials, new Callback<String>() {
            @Override
            public void success(String continentResponse, Response response) {
                JSONObject rootObj = null;
                try {
                    rootObj = new JSONObject(continentResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Example regResponse = new Gson().fromJson(rootObj.toString(), Example.class);
                String strBronzeBadgeCount=""+regResponse.getItems().get(0).getBadgeCounts().getBronze();
                String strSilver=""+regResponse.getItems().get(0).getBadgeCounts().getSilver();
                String strGold=""+regResponse.getItems().get(0).getBadgeCounts().getGold();
                String strBadgeValues="Bronze :"+strBronzeBadgeCount+" Silver :"+strSilver+" Gold :"+strGold;
                tvBadgeCount.setText(strBadgeValues);
                tvReputation.setText(""+regResponse.getItems().get(0).getReputation());
                tvQuestionCount.setText(""+regResponse.getItems().get(0).getQuestionCount());
                tvAccountId.setText(""+regResponse.getItems().get(0).getAccountId());
                tvCreation.setText(""+AppConstants.getDate(regResponse.getItems().get(0).getCreationDate()));
                tvUserId.setText(""+regResponse.getItems().get(0).getUserId());
                progressWheel.stopSpinning();
            }

            @Override
            public void failure(RetrofitError error) {
                progressWheel.stopSpinning();
            }
        });

    }
}
