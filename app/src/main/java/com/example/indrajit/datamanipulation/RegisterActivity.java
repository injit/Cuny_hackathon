package com.example.indrajit.datamanipulation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends Activity {

    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final String[] spinnerText = new String[1];


        final EditText userName = (EditText) findViewById(R.id.txtUserNameRegister);
        final EditText password = (EditText) findViewById(R.id.txtPasswordRegister);
        final EditText email = (EditText) findViewById(R.id.txtEmailRegister);
        final Button register = (Button) findViewById(R.id.btnRegister);
        final TextView signup = (TextView) findViewById(R.id.txtSigninNow);

        Utility.customView(register, ContextCompat.getColor(this, R.color.color_button));



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameuser = userName.getText().toString().toLowerCase().trim();
                String emailAddr = email.getText().toString().toLowerCase().trim();
                String pass = password.getText().toString().trim();
                String dataType = "register";
                if (!nameuser.isEmpty() && !emailAddr.isEmpty() && !pass.isEmpty()) {
                    BackgroundWorker bg = new BackgroundWorker(getApplicationContext());


                    try {
                        String jsonStr = bg.execute(dataType, nameuser, emailAddr, pass).get();
                        Log.d(LOG_TAG, jsonStr);
                        JSONObject jObj = new JSONObject(jsonStr);
                        boolean error = jObj.getBoolean("error");

                        // Check for error node in json
                        if (!error) {
                            // user successfully logged in
                            // Create login session

                            // Now store the user in SQLite


                            JSONObject user = jObj.getJSONObject("user");
                            String uid = user.getString("id");
                            String name = user.getString("username");
                            String email = user.getString("email");

                            // Inserting row in users table
                            Log.d(LOG_TAG, "user : " + name + "\tEmail : " + email);
                            // Launch main activity
                            Intent intent = new Intent(RegisterActivity.this,
                                    LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Error in login. Get the error message
                            String errorMsg = jObj.getString("error-reason");
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


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
