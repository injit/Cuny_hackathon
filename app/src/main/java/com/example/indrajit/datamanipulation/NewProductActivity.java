package com.example.indrajit.datamanipulation;

//

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import android.widget.Toast;


public class NewProductActivity extends Activity {

    EditText Name, Price, Description;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        /*
        renders the back arrow on the left of the screen
         */
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        /**
         * gets the inputs from the user
         */
        Name = (EditText) findViewById(R.id.inputName);
        Price = (EditText) findViewById(R.id.inputPrice);
        Description = (EditText) findViewById(R.id.inputDesc);
        add = (Button) findViewById(R.id.btnCreateProduct);

    }

    /*
     *navigates to the other page
     */
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
    public void AddProduct(View view) {
        String name = Name.getText().toString();
        String price = Price.getText().toString();
        String description = Description.getText().toString();
        String type = "add_product";
        if (name.isEmpty() || price.isEmpty() || description.isEmpty()) {
            Toast.makeText(NewProductActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
        } else {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, name, price, description);
            Intent i = new Intent(getApplicationContext(),
                    MainActivity.class);
            // Closing all previous activities
            startActivity(i);
        }
    }
}
