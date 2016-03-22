package stack.com.stackapi.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.internal.Util;

import model.question.QuestionStackResponse;
import stack.com.stackapi.R;
import stack.com.stackapi.utils.AppConstants;


public class QuestionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean isLikesAdapter = false;
    private boolean isLive = false;
    QuestionStackResponse postResponse;
    private String strUserImage;
    private Context context;
    public void addPosts ( QuestionStackResponse regResponse,Context context ) {
        this.postResponse=regResponse;
        this.context=context;
        isLive=true;
        notifyDataSetChanged();
    }

    private class QuestionHolder extends RecyclerView.ViewHolder {
        public final View itemView;
        public SimpleDraweeView userImage;
        private TextView tvPostTitle, tvUserName,tvAnswerCount,tvReputation;
        private TextView tvTimeStamp;
        public RatingBar rvPost_list_ratingvalues;
        private Button btTag;
        public QuestionHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            userImage = (SimpleDraweeView) itemView.findViewById(R.id.list_user_img);
            tvPostTitle = (TextView) itemView.findViewById(R.id.title_question);
            tvUserName = (TextView) itemView.findViewById(R.id.name);
            tvTimeStamp =(TextView)itemView.findViewById(R.id.timestamp);
            tvReputation =(TextView)itemView.findViewById(R.id.reputation);
            tvAnswerCount =(TextView)itemView.findViewById(R.id.answercount);
            btTag=(Button)itemView.findViewById(R.id.tag);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contactsView;
        contactsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_row_items, parent, false);
        return new QuestionHolder(contactsView);
        
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        QuestionHolder questionHolder=(QuestionHolder)holder;
        //User Name

        questionHolder.tvUserName.setText(postResponse.getItems().get(position).getOwner().getDisplayName());
        //Title Of the Post
        questionHolder.tvPostTitle.setText(postResponse.getItems().get(position).getTitle());
        //Link of the Post
        questionHolder.tvTimeStamp.setText("" + AppConstants.getDate(postResponse.getItems().get(position).getCreationDate()));
        Log.d("Date", AppConstants.getDate(postResponse.getItems().get(position).getCreationDate()));
        //Reputation of the user
        questionHolder.tvReputation.setText(""+postResponse.getItems().get(position).getOwner().getReputation());
        //Answer Count
        questionHolder.tvAnswerCount.setText("" + postResponse.getItems().get(position).getAnswerCount());
        questionHolder.userImage.setImageURI(Uri.parse(postResponse.getItems().get(position).getOwner().getProfileImage()));
        questionHolder.btTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag="";
                for (int i=0;i<postResponse.getItems().get(position).getTags().size();i++) {
                    tag=tag+","+postResponse.getItems().get(position).getTags().get(i);
                }
                tag=tag.substring(1,tag.length());
                Toast.makeText(context,tag,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return isLive ? postResponse.getItems().size() : 0;
    }


}
