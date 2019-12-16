package mx.com.marveldemo.custom;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import mx.com.marveldemo.R;

public class LoadingView {


    private Activity activity;
    private Dialog dialog;
    private com.github.loadingview.LoadingView loadingView;


    public LoadingView(Activity activity) {
        this.activity = activity;
    }





    public void showDialog() {

        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(dialog.getWindow()!=null){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        }
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading);



        dialog.show();
        loadingView = dialog.findViewById(R.id.loadingView);


    }


    public void start(){
        loadingView.start();
    }

    public void stop(){
        loadingView.stop();
    }



    public void hideDialog(){
        dialog.dismiss();
    }
}
