package com.esad.group.assignment.two.dev.inspector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.adapter.InspectorTokenHistoryAdapter;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.modal.PreviousTokens;
import com.esad.group.assignment.two.dev.modal.TokenHistory;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ViewPreviousApprovedTicketsActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    private List<Object> viewItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_previous_approved_tickets);
        ButterKnife.bind(this);
        init();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.token_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new InspectorTokenHistoryAdapter(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        addItemsFromJSON();
    }

    //innitial data load
    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getInstance();
        mUserName.setText(appUserSingleton.getFirstName() + " "+ appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }

    @OnClick(R.id.signout)
    public void signOut() {
//        popup message
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    //navigate back
    @OnClick(R.id.btn_back)
    public void navigateBack() {
        onBackPressed();
    }

    private void addItemsFromJSON() {
        // getting data from JSON and setting to the model
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            // looping through the json file to create models
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                int id = itemObj.getInt("id");
                String date = itemObj.getString("date");
                String busNumber = itemObj.getString("bus_number");
                String status = itemObj.getString("status");
                TokenHistory previousTokens = new TokenHistory(id, date, busNumber, status);
                viewItems.add(previousTokens);
            }
        } catch (JSONException | IOException e) {
            // display errors
            Log.d("LOG", "addItemsFromJSON: ", e);
        }
    }

    //read json file
    private String readJSONDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        // read JSON file to fetch data
        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.ticket_history);
            //reading data --
            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                while ((jsonString = bufferedReader.readLine()) != null) {
                    builder.append(jsonString);
                }
            } catch (Exception e) {
                // display errors
                Log.d("LOG", "Error : ", e);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}