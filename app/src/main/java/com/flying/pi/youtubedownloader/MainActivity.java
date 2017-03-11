package com.flying.pi.youtubedownloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        handleSignInResult("metallica");

        testAuth();

    }

    private void testAuth() {


    }


//    private void handleSignInResult(final String queryTerm) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    // This object is used to make YouTube Data API requests. The last
//                    // argument is required, but since we don't need anything
//                    // initialized when the HttpRequest is initialized, we override
//                    // the interface and provide a no-op function.
//                    YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
//                        public void initialize(HttpRequest request) throws IOException {
//                        }
//                    }).setApplicationName("youtube-cmdline-search-sample").build();
//
//
//                    // Define the API request for retrieving search results.
//                    YouTube.Search.List search = youtube.search().list("id,snippet");
//
//                    // Set your developer key from the {{ Google Cloud Console }} for
//                    // non-authenticated requests. See:
//                    // {{ https://cloud.google.com/console }}
//                    String apiKey = "AIzaSyBogsoz7Bw3GKXBr8itjhVtcXEaVv8hVY8";
//                    search.setKey(apiKey);
//                    search.setQ(queryTerm);
//
//                    // Restrict the search results to only include videos. See:
//                    // https://developers.google.com/youtube/v3/docs/search/list#type
//                    search.setType("video");
//
//                    // To increase efficiency, only retrieve the fields that the
//                    // application uses.
//                    search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
//                    search.setMaxResults((long) 30);
//
//                    // Call the API and print results.
//                    SearchListResponse searchResponse = search.execute();
//                    List<SearchResult> searchResultList = searchResponse.getItems();
//                    if (searchResultList != null) {
//                        showResult(searchResultList);
//                    }
//
//                } catch (GoogleJsonResponseException e) {
//                    System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
//                            + e.getDetails().getMessage());
//                } catch (IOException e) {
//                    System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
//                } catch (Throwable t) {
//                    t.printStackTrace();
//                }
//            }
//        }).start();
//
//    }
//
//    private void showResult(List<SearchResult> searchResultList) {
//        Log.i("MYyoutube", "searchResultList " + searchResultList);
//    }
}






/*
package com.flying.pi.youtubedownloader;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 1342;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestId()
                .requestScopes(new Scope(Scopes.PROFILE))
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                .enableAutoManage(MainActivity.this, MainActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        tv.setText(stringFromJNI());
        findViewById(R.id.test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

    }

public native String stringFromJNI();

// Used to load the 'native-lib' library on application startup.
static {
        System.loadLibrary("native-lib");
        }

@Override
public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("MYyoutube", "onConnectionFailed" + connectionResult);
        }

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        handleSignInResult(result);
        }
        }

private void handleSignInResult(GoogleSignInResult result) {
        Log.d("MYyoutube", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
        // Signed in successfully, show authenticated UI.
        GoogleSignInAccount acct = result.getSignInAccount();


        new Thread(new Runnable() {
@Override
public void run() {
        try {
        // This object is used to make YouTube Data API requests. The last
        // argument is required, but since we don't need anything
        // initialized when the HttpRequest is initialized, we override
        // the interface and provide a no-op function.
        YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
public void initialize(HttpRequest request) throws IOException {
        }
        }).setApplicationName("youtube-cmdline-search-sample").build();

        // Prompt the user to enter a query term.
        String queryTerm = "metallica";

        // Define the API request for retrieving search results.
        YouTube.Search.List search = youtube.search().list("id,snippet");

        // Set your developer key from the {{ Google Cloud Console }} for
        // non-authenticated requests. See:
        // {{ https://cloud.google.com/console }}
        String apiKey = "AIzaSyBogsoz7Bw3GKXBr8itjhVtcXEaVv8hVY8";
        search.setKey(apiKey);
        search.setQ(queryTerm);

        // Restrict the search results to only include videos. See:
        // https://developers.google.com/youtube/v3/docs/search/list#type
        search.setType("video");

        // To increase efficiency, only retrieve the fields that the
        // application uses.
        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
        search.setMaxResults((long) 30);

        // Call the API and print results.
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();
        if (searchResultList != null) {
        showResult(searchResultList);
        }
        } catch (GoogleJsonResponseException e) {
        System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
        + e.getDetails().getMessage());
        } catch (IOException e) {
        System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
        t.printStackTrace();
        }
        }
        }).start();

        } else {
        // Signed out, show unauthenticated UI.
        }

        }

private void showResult(List<SearchResult> searchResultList) {
        Log.i("MYyoutube","searchResultList "+ searchResultList);
        }
        }

 */