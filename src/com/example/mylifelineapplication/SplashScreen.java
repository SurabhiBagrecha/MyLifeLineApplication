package com.example.mylifelineapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


public class SplashScreen extends Activity {

    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
         Toast.makeText(SplashScreen.this,
                        "- Start -",
                        Toast.LENGTH_LONG).show();
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();

            }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void>{

        boolean running;
        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Void... params) {
            int i = 10;
            while(running){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(i-- == 0){
                    running = false;
                }

                publishProgress(i);

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setMessage(String.valueOf(values[0]));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;

            progressDialog = ProgressDialog.show(SplashScreen.this,
                    "ProgressDialog",
                    "Wait!");

            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    running = false;
                }
            });

            Toast.makeText(SplashScreen.this,
                    "Progress Start",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
           Intent startUp = new Intent(SplashScreen.this,StartUpActivity.class);
           startActivity(startUp);
        }

    }

}