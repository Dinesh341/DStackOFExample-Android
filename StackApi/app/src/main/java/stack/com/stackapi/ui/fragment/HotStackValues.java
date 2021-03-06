/**
 * @category DineshSample
 * @author Dinesh <dineshbabu341@gmail.com>
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
package stack.com.stackapi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import model.question.QuestionStackResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import stack.com.stackapi.R;
import stack.com.stackapi.adapter.QuestionListAdapter;
import stack.com.stackapi.api.RetrofitRequestHelper;
import stack.com.stackapi.utils.AppConstants;


public class HotStackValues extends Fragment {
    private ProgressWheel progressWheel;
    private RecyclerView hotQuestionData;
    private QuestionListAdapter postListAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.hot_stackdatalist, container, false);
}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressWheel = (ProgressWheel)view.findViewById(R.id.progress_wheel);
        progressWheel.stopSpinning();
        hotQuestionData = (RecyclerView) view.findViewById(R.id.hot_list_recyler);
        hotQuestionData.setLayoutManager(new LinearLayoutManager(hotQuestionData.getContext()));
        postListAdapter = new QuestionListAdapter();
        hotQuestionData.setAdapter(postListAdapter);
        if(AppConstants.isInternetAvailable(getActivity())) {
            getQuestionDetails();
        }else{
            Toast.makeText(getActivity(), getString(R.string.internet), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Get the Questions on Hot Categories
     */
    private void getQuestionDetails() {
        progressWheel.spin();
        HashMap<String,String> userCredentials=new HashMap<>();
        userCredentials.put(AppConstants.CommonAPI.ORDER,AppConstants.CommonAPI.DESC);
        userCredentials.put(AppConstants.CommonAPI.SORT,AppConstants.CommonAPI.HOT);
        userCredentials.put(AppConstants.CommonAPI.SITE,AppConstants.CommonAPI.STACKOVERFLOW);
        new RetrofitRequestHelper().getInstance().getAnswerDetails(userCredentials, new Callback<String>() {
            @Override
            public void success(String continentResponse, Response response) {
                JSONObject rootObj = null;
                try {
                    rootObj = new JSONObject(continentResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                QuestionStackResponse regResponse = new Gson().fromJson(rootObj.toString(), QuestionStackResponse.class);
                postListAdapter.addPosts(regResponse, getActivity());
                progressWheel.stopSpinning();
            }

            @Override
            public void failure(RetrofitError error) {
                progressWheel.stopSpinning();
            }
        });

    }
}
