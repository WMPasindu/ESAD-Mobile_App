package com.esad.group.assignment.two.dev.passenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.esad.group.assignment.two.dev.LoginActivity;
import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.adapter.PassengerLogAdapter;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.modal.PassengerLog;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class PassengerLogActivity extends AppCompatActivity {

    private List<Object> viewItems = new ArrayList<>();
    private static final String TAG = "ACTIVITY";
    private Bitmap bitmap;
    @BindView(R.id.name)
    TextView mUserName;
    @BindView(R.id.txt_email)
    TextView mEmail;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.PassengerLogLinearLayout)
    ConstraintLayout PassengerLogLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_log);
        ButterKnife.bind(this);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new PassengerLogAdapter(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        addItemsFromJSON();
        init();
    }

    private void addItemsFromJSON() {
        try {
            // getting data from JSON and setting to the model
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            // looping through the json file to create models
            for (int i=0; i<jsonArray.length(); ++i) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String id = itemObj.getString("id");
                String date = itemObj.getString("date");
                String departure = itemObj.getString("departure");
                String arrival = itemObj.getString("arriaval");
                PassengerLog holidays = new PassengerLog(id, date, departure, arrival);
                viewItems.add(holidays);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }


    private String readJSONDataFromFile() throws IOException{
        // read JSON file to fetch data
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.passenger_log_data);
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

    // download report option
    @OnClick(R.id.btn_download_report)
    public void downloadReport() {
        bitmap = loadBitmapFromView(PassengerLogLinearLayout, PassengerLogLinearLayout.getWidth(), PassengerLogLinearLayout.getHeight());
        createPdf();
    }

    // navigate to previous activity
    @OnClick(R.id.btn_back)
    public void navigateBack() {
        onBackPressed();
    }

    // signout option
    @OnClick(R.id.signout)
    public void signOut() {
        Intent mIntent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(mIntent);
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    //This method is to create the PDF from the LinearLayout
    private void createPdf() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;

        int convertHeight = (int) height;
        int convertWidth = (int) width;
        //creating a pdf herer -- todo -> emulator functionality is not working
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHeight, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHeight, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        //Write the document Content
        String targetPdf = "/sdcard/pastJourneyLog.pdf";
        File filePath;

        filePath = new File(targetPdf);

        try {
            document.writeTo(new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), e.toString(), BaseTransientBottomBar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            snackbar.show();
            e.printStackTrace();
        }

        //close the document
        document.close();
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Report Created !", BaseTransientBottomBar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        snackbar.show();
        openGeneratedPDF();
    }

    //This method is to open the generated PDF
    private void openGeneratedPDF() {
        File file = new File("/sdcard/pastJourneyLog.pdf");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "No Application to view PDF", BaseTransientBottomBar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                snackbar.show();
            }
        }
    }

    protected void init() {
        AppUserSingleton appUserSingleton = AppUserSingleton.getINSTANCE();
        mUserName.setText(appUserSingleton.getFirstName() + " " + appUserSingleton.getLastname());
        mEmail.setText(appUserSingleton.getEmail());
        Picasso.get().load(appUserSingleton.getProfileImage()).noFade().fit().into(mProfileImage);
    }
}