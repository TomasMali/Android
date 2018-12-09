package com.example.tomas.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean JobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"Job Started..");

        doBackgroundJob(params);

        return true;
    }

    private void doBackgroundJob(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {


                for (int i =0; i<10; i++){
                    Log.d(TAG, "run: " + i);

                    if(JobCancelled==true)
                        return;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG, "Job finished");
                jobFinished(params,false);

            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG,"Job cancelled before completion");
        JobCancelled = true;
        return false;
    }
}
