package com.example.temp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class Check  extends AsyncTask<String,Void,String>{

   private TextView statusField,roleField;
   private Context context;
   private int byGetOrPost = 0; 
   //flag 0 means get and 1 means post.(By default it is get.)
   public Check(Context context,TextView roleField) {
      this.context = context;
      this.roleField = roleField;
   }

   protected void onPreExecute(){

   }
   @Override
   protected String doInBackground(String... arg0) {
      
     
         try{
            String str = (String)arg0[0];
            String link="http://www.sachin007.comlu.com/check.php";
            String data  = URLEncoder.encode("str", "UTF-8") 
             + "=" + URLEncoder.encode(str, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection(); 
            conn.setDoOutput(true); 
            OutputStreamWriter wr = new OutputStreamWriter
            (conn.getOutputStream());
            wr.write( data ); 
            wr.flush(); 
            BufferedReader reader = new BufferedReader
            (new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null)
            {
               sb.append(line);
               break;
            }
            return sb.toString();
         }catch(Exception e){
            return new String("Exception: " + e.getMessage());
         }
      }
   
   
   @Override
   protected void onPostExecute(String result){
	   this.roleField.setText(result);
	   
   }
	   }