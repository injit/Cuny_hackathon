package com.example.indrajit.datamanipulation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indrajit.datamanipulation.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends Activity {
    private final String TAG = getClass().getSimpleName();
    private EditText userName;
    private EditText passWord;
    private Button login;
    private CheckBox remember;
    private TextView register;
    private ProgressDialog pDialog;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.txtUserNameLogin);
        passWord = (EditText) findViewById(R.id.txtPasswordLogin);
        login = (Button) findViewById(R.id.btnLogin);
        remember = (CheckBox) findViewById(R.id.chkRemember);
        register = (TextView) findViewById(R.id.txtRegisterNow);

        Utility.customView(login, ContextCompat.getColor(this, R.color.color_button));
        session = new SessionManager(getApplicationContext());

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        if (session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = userName.getText().toString().toLowerCase().trim();
                String pass = passWord.getText().toString().trim();
                String dataType = "login";
                if (!username.isEmpty() && !pass.isEmpty()) {
                    BackgroundWorker bg = new BackgroundWorker(getApplicationContext());
                    try {
                        String jsonStr = bg.execute(dataType, username, pass).get();
                        Log.d(TAG, jsonStr);
                        JSONObject jObj = new JSONObject(jsonStr);
                        boolean error = jObj.getBoolean("error");

                        // Check for error node in json
                        if (!error) {
                            // user successfully logged in
                            // Create login session
                            session.setLogin(true);

                            // Now store the user in SQLite


                            JSONObject user = jObj.getJSONObject("user");
                            String uid = user.getString("id");
                            String name = user.getString("username");
                            String email = user.getString("email");
                            String type = user.getString("type");

                            // Inserting row in users table
                            Log.d(TAG, "user : " + name + "\tEmail : " + email + "\tType : " + type);
                            // Launch main activity


                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // Error in login. Get the error message
                            String errorMsg = jObj.getString("error-message");
                            Toast.makeText(getApplicationContext(),
                                    errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (InterruptedException | ExecutionException | JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
