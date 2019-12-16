package mx.com.marveldemo.mvp.ui.main;

import android.content.Context;


import mx.com.marveldemo.model.GenerialError;
import mx.com.marveldemo.model.Hero;
import mx.com.marveldemo.model.apimodel.ApiGetHeroesResponse;

public interface MainContract {



    interface presenter{
        void onDestroy();
        void onRefreshClickButotn();
        void fetchData();
        void onHeroClicked(Hero hero);
    }


    interface MainView{
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ApiGetHeroesResponse myheroes);

        void onResponseFailure(GenerialError error);

        void updateHeroInfo(Hero hero);
    }

    interface NetTaskListener{

        interface  OnResultInterface{
            void onResultOk(ApiGetHeroesResponse myheroes);
            void onError(GenerialError error);
        }

        void getHeroesArray(OnResultInterface onResultInterface, Context context);
    }




}
