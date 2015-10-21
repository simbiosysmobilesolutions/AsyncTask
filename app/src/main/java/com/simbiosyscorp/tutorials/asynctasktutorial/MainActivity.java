package com.simbiosyscorp.tutorials.asynctasktutorial;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    ProgressBar progressBar;
    Button StartAsync;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartAsync = (Button) findViewById(R.id.startprogress);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_Horizontal);

        StartAsync.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                new BackgroundAsyncTask().execute();

                StartAsync.setClickable(false);
            }
        });
    }

    public class BackgroundAsyncTask extends AsyncTask<Void, Integer, Void> {

        int myProgressCount;
        //Executes on UI/Main thread , in preparation to execute doInBackground
        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this,
                    "onPreExecute Start Progress Bar", Toast.LENGTH_LONG)
                    .show();
            //Initializing the Progress Bar
            progressBar.setProgress(0);
            myProgressCount = 0;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            while (myProgressCount < 100) {
                myProgressCount++;
                /**
                 * Runs on the UI thread after publishProgress(Progress...) is
                 * invoked.
                 */

                publishProgress(myProgressCount);
                SystemClock.sleep(100);
            }
            return null;
        }

        /**
         * The publishProgress() method will trigger the execution of
         * onProgressUpdate(Progress...) on the UI thread.
         *
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(MainActivity.this, "onPostExecute End Progress Bar",
                    Toast.LENGTH_LONG).show();

            StartAsync.setClickable(true);
        }
    }
}
