package mx.com.marveldemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mx.com.marveldemo.R;
import mx.com.marveldemo.custom.LoadingView;

public class BaseActivity extends AppCompatActivity {


    private  boolean backEnabled;
    private LoadingView viewDialog;
    private boolean isShowing = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backEnabled = true;

        viewDialog = new LoadingView(BaseActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!isShowing) {
                    viewDialog.showDialog();
                    viewDialog.start();

                    isShowing = true;
                }
            }
        });


    }


    public void hideProgress() {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isShowing) {
                    viewDialog.hideDialog();
                    viewDialog.stop();

                    isShowing = false;

                }
            }
        });


    }


    public void setBackEnabled(boolean backEnabled) {
        this.backEnabled = backEnabled;
    }

    @Override
    public void onBackPressed() {

        if (backEnabled) {
            super.onBackPressed();
        } else {
            moveTaskToBack(true);
        }

    }


    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

    }


    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

    }

}