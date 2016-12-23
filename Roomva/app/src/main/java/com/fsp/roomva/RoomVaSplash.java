package com.fsp.roomva;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;

public class RoomVaSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_va_splash);
    }

    @Override
    public void onStart() {
        super.onStart();

        Branch branch = Branch.getInstance();

        branch.initSession(new Branch.BranchUniversalReferralInitListener() {
            @Override
            public void onInitFinished(BranchUniversalObject branchUniversalObject, LinkProperties linkProperties, BranchError branchError) {
                //If not Launched by clicking Branch link
                TextView display = (TextView) findViewById(R.id.Display);
                if (branchUniversalObject == null) {
                    display.setText("No Link clicked, Hello from Branch");
                } else {
                    JSONObject display_json = branchUniversalObject.convertToJson();
                    String display_text = display_json.toString();
                    display.setText(display_text);
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }
}
