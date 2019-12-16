package mx.com.marveldemo.mvp.ui.main;

import android.content.Context;

import mx.com.marveldemo.model.GenerialError;
import mx.com.marveldemo.model.Hero;
import mx.com.marveldemo.model.apimodel.ApiGetHeroesResponse;
import mx.com.marveldemo.utils.AppUtils;

public class MainPresenterImpl implements MainContract.presenter,MainContract.NetTaskListener.OnResultInterface {



    private MainContract.MainView mainView;
    private MainContract.NetTaskListener netTaskListener;
    private Context context;



    public MainPresenterImpl(MainContract.MainView mainView, MainContract.NetTaskListener netTaskListener,Context context) {
        this.mainView = mainView;
        this.netTaskListener = netTaskListener;
        this.context = context;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshClickButotn() {

        if(mainView != null){
            mainView.showProgress();
        }
        if(AppUtils.isNetworkAvailable(context)){
            netTaskListener.getHeroesArray(this,context);
        }


    }

    @Override
    public void fetchData() {
        if(mainView != null){
            mainView.showProgress();
        }
        if(AppUtils.isNetworkAvailable(context)){
            netTaskListener.getHeroesArray(this,context);
        }
    }

    @Override
    public void onHeroClicked(Hero hero) {
        if(mainView!=null){
            mainView.updateHeroInfo(hero);
        }
    }

    @Override
    public void onResultOk(ApiGetHeroesResponse myheroes) {

        if(mainView != null){
            mainView.setDataToRecyclerView(myheroes);
            mainView.hideProgress();
        }

    }

    @Override
    public void onError(GenerialError error) {
        if(mainView != null){
            mainView.onResponseFailure(error);
            mainView.hideProgress();
        }

    }
}
