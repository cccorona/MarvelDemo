package mx.com.marveldemo.mvp.ui.main;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import mx.com.marveldemo.R;
import mx.com.marveldemo.adapters.HeroesAdapter;
import mx.com.marveldemo.base.BaseActivity;
import mx.com.marveldemo.interfaces.HeroSelectedInterface;
import mx.com.marveldemo.model.GenerialError;
import mx.com.marveldemo.model.Hero;
import mx.com.marveldemo.model.apimodel.ApiGetHeroesResponse;


public class MainActivity extends BaseActivity implements MainContract.MainView , HeroSelectedInterface  {



    private RecyclerView list;
    private ImageView refresh;
    private CardView detailCard;
    private TextView name,
                     realName,
                     height,
                     power,
                     abilities,
                     groups;

    private ImageView largePic;

    private MainContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setBackEnabled(false);
        presenter = new MainPresenterImpl(this,new NetTaskListenerImpl(),this);
        presenter.fetchData();
    }



    private void initViews(){
      list = findViewById(R.id.heros_list);
      refresh = findViewById(R.id.refresh);
      detailCard = findViewById(R.id.detail_card);
      largePic = findViewById(R.id.large_pic);
      name = findViewById(R.id.name);
      realName = findViewById(R.id.real_name);
      height = findViewById(R.id.height);
      power = findViewById(R.id.power);
      abilities = findViewById(R.id.abilities);
      groups = findViewById(R.id.grupos);


      list.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));
      refresh.setOnClickListener(v -> presenter.onRefreshClickButotn());
    }

    @Override
    public void showProgress() {
        super.showProgress();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
    }

    @Override
    public void setDataToRecyclerView(ApiGetHeroesResponse myheroes) {

        HeroesAdapter heroesAdapter = new HeroesAdapter(myheroes, MainActivity.this);
        list.setAdapter(heroesAdapter);

    }

    @Override
    public void onResponseFailure(GenerialError error) {
        Toast.makeText(MainActivity.this,
                getString(error.getDescription()),
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void updateHeroInfo(Hero hero) {
          detailCard.setVisibility(View.VISIBLE);
          name.setText(hero.getName());
          Glide.with(largePic).load(hero.getPhoto()).into(largePic);
          realName.setText(hero.getRealName());
          height.setText(hero.getHeight());
          power.setText(hero.getPower());
          abilities.setText(hero.getAbilities());
          groups.setText(hero.getGroups());



    }

    @Override
    public void onClickedHero(Hero hero) {
        //show dialog
        presenter.onHeroClicked(hero);
    }
}
