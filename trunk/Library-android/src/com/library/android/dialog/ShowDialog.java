package com.library.android.dialog;

import java.util.concurrent.TimeUnit;

import android.app.ProgressDialog;
import android.content.Context;

public class ShowDialog {

	
	public static void progressDialog(Context ctx, final long timeout){
        final ProgressDialog dialog = new ProgressDialog(ctx);
        dialog.setCancelable(true);
        dialog.setMessage("Loading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setProgress(0);
        dialog.setMax(100);
        dialog.show();
 
        Thread myThread = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.setProgress(100);
                dialog.dismiss();
            }
        });
        myThread.start();
	}

}
