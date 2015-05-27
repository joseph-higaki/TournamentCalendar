package com.japanigger.tournamentcalendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.japanigger.tournamentcalendar.data.Match;

import java.util.Arrays;


public class ViewMatch extends ActionBarActivity {

    private static final String PERMISO = "publish_actions";

    private Match match;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onSuccess(Sharer.Result result) {
            Log.d(getClass().getName(),"shareCallback on success");
            if (result.getPostId()!=null){
                Toast.makeText(ViewMatch.this,"Post publicado "+result.getPostId(),Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Log.d(getClass().getName(),"shareCallback on cancel");
        }

        @Override
        public void onError(FacebookException e) {
            Log.d(getClass().getName(),"shareCallback on error");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_match);

        callbackManager = CallbackManager.Factory.create();

        Log.d(getClass().getName(), "VIEW MATCH ACTIVITY");
        match = (Match) getIntent().getSerializableExtra("selectedMatch");
        Log.d(getClass().getName(), "object match: "+match);

        ((TextView)findViewById(R.id.tvMatchTeams)).setText(match.getTeam1().getName() + " vs. " + match.getTeam2().getName());
        ((TextView)findViewById(R.id.tvMatchCity)).setText(match.getLocation().toString());
        ((TextView)findViewById(R.id.tvMatchDateTime)).setText(match.getDate());

        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager,shareCallback);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_match, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public void facebookPost(View view) {
        String title = match.getTeam1().getName()+" VS "+match.getTeam2().getName();
        String content = "Encuentro entre "+match.getTeam1().getName()+" VS "+match.getTeam2().getName()+" el dia "+match.getDate()+" en "+match.getLocation().getName();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken!=null){
            boolean permisos=accessToken.getPermissions().contains(PERMISO);
            if (permisos){
                Profile profile = Profile.getCurrentProfile();
                ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                        .setContentTitle(title)
                        .setContentDescription(content)
                        .setContentUrl(Uri.parse("www.google.com"))
                        .build();
                shareDialog.show(shareLinkContent);
            }else{
                LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList(PERMISO));
            }
        }else{
            LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList(PERMISO));
        }
    }
}
