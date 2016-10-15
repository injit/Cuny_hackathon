package com.example.indrajit.datamanipulation;

import android.graphics.drawable.GradientDrawable;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by mofi on 9/6/16.
 */
public class Utility {
    public static boolean validatePassword(String str) {
        //this is not necessary because we will check for this before calling the method
        if (!(str.length() > 0)) {
            return false;
        }

        //Password must be at least 8 character long
        boolean hasAtLeastEight = str.length() >= 8;

        //Password needs at least one uppercase letter
        boolean hasUpper = !str.equals(str.toLowerCase());

        //Password needs at least one lowercase letter
        boolean hasLower = !str.equals(str.toUpperCase());

        //password needs at least one special character
        boolean hasSpecial = !str.matches("[A-Za-z0-9]");

        return hasAtLeastEight && hasUpper && hasLower && hasSpecial;
    }

    public static String getHashPassword(String str) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] byteOfMessage = str.getBytes("UTF-8");
            byte[] digest = md.digest(byteOfMessage);
            result = new String(digest, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void customView(View v, int backgroundColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(R.dimen.shape_radius);
        shape.setColor(backgroundColor);
        v.setBackground(shape);
    }

    private static JSONObject getJsonObjectFromMap(Map params) throws JSONException {

        //all the passed parameters from the post request
        //iterator used to loop through all the parameters
        //passed in the post request
        Iterator iter = params.entrySet().iterator();

        //Stores JSON
        JSONObject holder = new JSONObject();

        //using the earlier example your first entry would get email
        //and the inner while would get the value which would be 'foo@bar.com'
        //{ fan: { email : 'foo@bar.com' } }

        //While there is another entry
        while (iter.hasNext()) {
            //gets an entry in the params
            Map.Entry pairs = (Map.Entry) iter.next();

            //creates a key for Map
            String key = (String) pairs.getKey();

            //Create a new map
            Map m = (Map) pairs.getValue();

            //object for storing Json
            JSONObject data = new JSONObject();

            //gets the value
            Iterator iter2 = m.entrySet().iterator();
            while (iter2.hasNext()) {
                Map.Entry pairs2 = (Map.Entry) iter2.next();
                data.put((String) pairs2.getKey(), (String) pairs2.getValue());
            }

            //puts email and 'foo@bar.com'  together in map
            holder.put(key, data);
        }
        return holder;
    }
}
