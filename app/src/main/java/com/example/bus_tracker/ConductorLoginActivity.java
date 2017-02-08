package com.example.bus_tracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static android.widget.Toast.LENGTH_LONG;


/**
 * Created by ronak on 7/2/17.
 */

public class ConductorLoginActivity extends AppCompatActivity{

    /**
     * Mobile Service Client reference
     */
    private MobileServiceClient mClient;

    /**
     * Mobile Service Table used to access data
     */
    private MobileServiceTable<Conductors> mConductorTable;

    /**
     * Progress spinner to use for table operations
     */
   // private ProgressBar mProgressBar;

    private CoordinatorLayout coordinatorLayout;
    private EditText CondIdEditText;
    private EditText CondPwdEditText;
    private Button submitButton;

    private String CondId;
    private String CondPwd;

    Context context;



    private View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Preliminary checks
            String notification = "";
            CondId = CondIdEditText.getText().toString();
            boolean missedEnrollmentNumber = false;
            if (CondId.trim().isEmpty()) {
                missedEnrollmentNumber = true;
            }
            CondPwd = CondPwdEditText.getText().toString();
            boolean missedPassword = false;
            if (CondPwd.trim().isEmpty()) {
                missedPassword = true;
            }
            if (missedEnrollmentNumber && missedPassword) {
                notification = "Please enter your credentials";
            } else {
                if (missedEnrollmentNumber) {
                    notification = "Please enter your Conductor Id";
                }
                if (missedPassword) {
                    notification = "Please enter your Password";
                }
            }

            if (missedEnrollmentNumber || missedPassword) {
                Snackbar snackbar = Snackbar.make(
                        coordinatorLayout,
                        notification,
                        Snackbar.LENGTH_LONG
                );
                snackbar.show();
            } else {
                //Disable the login button
                submitButton.setText(getResources().getString(R.string.logging_in));
                submitButton.setOnClickListener(null);

                //Hide keyboard
                try {
                    InputMethodManager inputManager =
                            (InputMethodManager) context.getApplicationContext()
                                    .getSystemService(Context.INPUT_METHOD_SERVICE);
                    View focusedView = ConductorLoginActivity.this.getCurrentFocus();
                    if (focusedView != null) {
                        inputManager.hideSoftInputFromWindow(
                                focusedView.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS
                        );
                    }
                } catch (NullPointerException exception) {
                    Log.e("Keyboard collapse", exception.getMessage());
                }

                //Do the login
                //TODO    getCsrfAndCallNext();



                MobileServiceList<Conductors> result = null;


                try {
                    result = mConductorTable.where()
                            .field("condId").eq(CondId)
                            .and()
                            .field("condPwd").eq(CondPwd)
                            .execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (result != null){
                    goToMain();
                }
                else {
                    Toast.makeText(ConductorLoginActivity.this, "Invalid Credentials",
                            LENGTH_LONG).show();
                }


                //If not using Retrofit
                //new LoginTask().execute();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductorlogin);

        updateDB();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }



        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.login_coordinator);

        ImageView logoImageView = (ImageView) findViewById(R.id.login_logo);
        CondIdEditText = (EditText) findViewById(R.id.username_edit_text);
        CondPwdEditText = (EditText) findViewById(R.id.password_edit_text);
        TextView logInTroubles = (TextView) findViewById(R.id.log_in_troubles);
        TextView madeWithLove = (TextView) findViewById(R.id.made_with_love);

        submitButton = (Button) findViewById(R.id.submit);
        submitButton.setTransformationMethod(null);
        submitButton.setOnClickListener(submitListener);

        String logInTroublesString = getResources().getString(R.string.log_in_troubles);
        SpannableStringBuilder logInTroublesSpannableStringBuilder = new SpannableStringBuilder(
                logInTroublesString
        );
        /**   ClickableSpan openImgSpan = new ClickableSpan() {
        @Override
        public void onClick(View view) {
        String url = "http://img.channeli.in/contact/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

        };
        String contactImg = "Contact Us";
        logInTroublesSpannableStringBuilder.setSpan(
        openImgSpan,
        logInTroublesString.indexOf(contactImg),
        logInTroublesString.indexOf(contactImg) + contactImg.length(),
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );*/
        logInTroubles.setMovementMethod(LinkMovementMethod.getInstance());
        logInTroubles.setText(logInTroublesSpannableStringBuilder, TextView.BufferType.SPANNABLE);
        logInTroubles.setGravity(Gravity.CENTER_HORIZONTAL);

        String creditsString = getResources().getString(R.string.credits);
        SpannableStringBuilder madeWithLoveSpannableStringBuilder = new SpannableStringBuilder(
                creditsString
        );
        ForegroundColorSpan redColoredSpan = new ForegroundColorSpan(
                ContextCompat.getColor(getApplicationContext(), R.color.colorRed)
        );
        String heart = "\u2665";
        madeWithLoveSpannableStringBuilder.setSpan(
                redColoredSpan,
                10,
                11,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        madeWithLove.setText(madeWithLoveSpannableStringBuilder, TextView.BufferType.SPANNABLE);

        logoImageView.setFocusableInTouchMode(true);
        logoImageView.requestFocus();

       // mProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);

        // Initialize the progress bar
       // mProgressBar.setVisibility(ProgressBar.GONE);

        try {
            // Create the Mobile Service Client instance, using the provided

            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://bus-tracker.azurewebsites.net",
                    this);

            // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });

            // Get the Mobile Service Table instance to use

            mConductorTable = mClient.getTable(Conductors.class);

            // Offline Sync
            //mToDoTable = mClient.getSyncTable("ToDoItem", ToDoItem.class);

            //Init local storage
            initLocalStore().get();



        } catch (MalformedURLException e) {
            createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
        } catch (Exception e){
            createAndShowDialog(e, "Error");
        }


    }



    private void updateDB() {


        CondId = "Raman";
        CondPwd = "Ram";
        addItem( CondId, CondPwd);
        CondId = "Raghav";
        CondPwd = "Rag";
        addItem( CondId, CondPwd);
        CondId = "Shahrukh";
        CondPwd = "Sha";
        addItem( CondId, CondPwd);
        CondId = "Salman";
        CondPwd = "Sal";
        addItem( CondId, CondPwd);
        CondId = "Hrithik";
        CondPwd = "Hri";
        addItem( CondId, CondPwd);


    }

    private void addItem( String CondId, String CondPwd){
        // Add item to table
        final Conductors item = new Conductors();
        item.setCondId(CondId);
        item.setCondPwd(CondPwd);

        // Insert the new item
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    addItemInTable(item);

                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }
        };

        runAsyncTask(task);

    }

    private void goToMain() {
        Intent intent = new Intent(
                getApplicationContext(),
                Main2Activity.class
        );
        intent.putExtra("LOGGED_IN", true);
        startActivity(intent);
        finish();
    }

    /**
     * Add an item to the Mobile Service Table
     *
     * @param item
     *            The item to Add
     */
    public void addItemInTable(Conductors item) throws ExecutionException, InterruptedException {

        Conductors entity;
        entity = mConductorTable.insert(item).get();


    }

    private AsyncTask<Void, Void, Void> initLocalStore() throws MobileServiceLocalStoreException, ExecutionException, InterruptedException {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    MobileServiceSyncContext syncContext = mClient.getSyncContext();

                    if (syncContext.isInitialized())
                        return null;

                    SQLiteLocalStore localStore = new SQLiteLocalStore(mClient.getContext(), "OfflineStore", null, 1);

                    Map<String, ColumnDataType> tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("condId", ColumnDataType.String);
                    tableDefinition.put("condPwd", ColumnDataType.String);

                    localStore.defineTable("Conductors", tableDefinition);

                    SimpleSyncHandler handler = new SimpleSyncHandler();

                    syncContext.initialize(localStore, handler).get();

                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        return runAsyncTask(task);
    }

    /**
     * Creates a dialog and shows it
     *
     * @param exception
     *            The exception to show in the dialog
     * @param title
     *            The dialog title
     */
    private void createAndShowDialogFromTask(final Exception exception, String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAndShowDialog(exception, "Error");
            }
        });
    }


    /**
     * Creates a dialog and shows it
     *
     * @param exception
     *            The exception to show in the dialog
     * @param title
     *            The dialog title
     */
    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if(exception.getCause() != null){
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }

    /**
     * Creates a dialog and shows it
     *
     * @param message
     *            The dialog message
     * @param title
     *            The dialog title
     */
    private void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }


    /**
     * Run an ASync task on the corresponding executor
     * @param task
     * @return
     */
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }


   /* private class ProgressFilter implements ServiceFilter {

        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback) {

            final SettableFuture<ServiceFilterResponse> resultFuture = SettableFuture.create();


            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.VISIBLE);
                }
            });

            ListenableFuture<ServiceFilterResponse> future = nextServiceFilterCallback.onNext(request);

            Futures.addCallback(future, new FutureCallback<ServiceFilterResponse>() {
                @Override
                public void onFailure(Throwable e) {
                    resultFuture.setException(e);
                }

                @Override
                public void onSuccess(ServiceFilterResponse response) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
                        }
                    });

                    resultFuture.set(response);
                }
            });

            return resultFuture;
        }
    }*/

}
