//package com.example.indrajit.datamanipulation;
//
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;
//
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import com.example.indrajit.datamanipulation.NewProductActivity.*;
//
///**
// * Created by indrajit on 9/12/16.
// */
//public class BackgroundWorker extends AsyncTask<String, Void, String> {
//    Context context;
//    private ProgressDialog pDialog;
//
//    AlertDialog alertDialog;
//    BackgroundWorker (Context ctx) {
//        context = ctx;
//    }
//    @Override
//    protected String doInBackground(String... params) {
//        String type = params[0];
//        String login_url = "http://10.0.2.2:8888/create_product.php";
//        if (type.equals("add_product")) {
//            try {
//                String name = params[1];
//                String price = params[2];
//                String desc = params[3];
//
//
//                    URL url = new URL(login_url);
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    System.out.print("after connection");
//                    httpURLConnection.setRequestMethod("POST");
//                    httpURLConnection.setDoOutput(true);
//                    httpURLConnection.setDoInput(true);
//                    OutputStream outputStream = httpURLConnection.getOutputStream();
//                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                    String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
//                            + URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8") + "&"
//                            + URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(desc, "UTF-8");
//                    bufferedWriter.write(post_data);
//                    bufferedWriter.flush();
//                    bufferedWriter.close();
//                    outputStream.close();
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
//                    String result = "";
//                    String line = "";
//                    while ((line = bufferedReader.readLine()) != null) {
//                        result += line;
//                    }
//                    bufferedReader.close();
//                    inputStream.close();
//                    httpURLConnection.disconnect();
//                    return result;
//
//                }catch(MalformedURLException e){
//                    e.printStackTrace();
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//
//        return null;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setMessage("Adding Product please wait...");
//        alertDialog.setCancelable(true);
//        alertDialog.show();
//        //alertDialog.setTitle("Adding Product");
//
//
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        alertDialog.setMessage(result);
//        alertDialog.dismiss();
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}


package com.example.indrajit.datamanipulation;

import android.content.Context;
import android.os.AsyncTask;

import com.example.indrajit.datamanipulation.App.AppConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by mofi on 9/12/16.
 */
public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;

    BackgroundWorker(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... values) {
        String type = values[0];
        String loginUrl = AppConfig.URL_LOGIN;
        String registerUrl = AppConfig.URL_REGISTER;
        String addCollege = AppConfig.URL_ADD_COLLEGE;
        String addClub = AppConfig.URL_ADD_CLUB;
        String getClub = AppConfig.URL_GET_CLUB;
        String getCollege = AppConfig.URL_GET_COLLEGE;


        String result = "";
        if (type.equals("login")) {
            try {
                String userName = values[1];
                String password = values[2];
                URL url = new URL(loginUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("register")) {
            try {
                String userName = values[1];
                String emailAddr = values[2];
                String password = values[3];
                String userType = values[4];
                URL url = new URL(registerUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(emailAddr, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(userType, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("add_college")) {
            try {
                String collegecode = values[1];
                String colllgename = values[2];
                URL url = new URL(addCollege);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("college_code", "UTF-8") + "=" + URLEncoder.encode(collegecode, "UTF-8") + "&" +
                        URLEncoder.encode("college_name", "UTF-8") + "=" + URLEncoder.encode(colllgename, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (type.equals("add_club")) {
            try {
                String clubName = values[1];
                String colllgeCode = values[2];
                String clubType = values[3];
                String clubEmail = values[4];
                String clubDesc = values[5];

                URL url = new URL(addClub);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("club_name", "UTF-8") + "=" + URLEncoder.encode(clubName, "UTF-8") + "&" +
                        URLEncoder.encode("college_id", "UTF-8") + "=" + URLEncoder.encode(colllgeCode, "UTF-8") + "&" +
                        URLEncoder.encode("club_type", "UTF-8") + "=" + URLEncoder.encode(clubType, "UTF-8") + "&" +
                        URLEncoder.encode("club_email", "UTF-8") + "=" + URLEncoder.encode(clubEmail, "UTF-8") + "&" +
                        URLEncoder.encode("club_desc", "UTF-8") + "=" + URLEncoder.encode(clubDesc, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (type.equals("get_club")) {
            try {
                String collegeId = values[1];
                URL url = new URL(getClub);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("college_id", "UTF-8") + "=" + URLEncoder.encode(collegeId, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();

                httpURLConnection.disconnect();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String value) {
        super.onPostExecute(value);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
