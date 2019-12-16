package mx.com.marveldemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;



import de.hdodenhof.circleimageview.CircleImageView;
import mx.com.marveldemo.R;
import mx.com.marveldemo.interfaces.HeroSelectedInterface;
import mx.com.marveldemo.model.Hero;
import mx.com.marveldemo.model.apimodel.ApiGetHeroesResponse;

public class HeroesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ApiGetHeroesResponse apiGetHeroesResponse;

    private HeroSelectedInterface heroSelectedInterface;





    public HeroesAdapter(ApiGetHeroesResponse apiGetHeroesResponse,HeroSelectedInterface heroSelectedInterface) {
        this.apiGetHeroesResponse = apiGetHeroesResponse;
        this.heroSelectedInterface = heroSelectedInterface;
    }





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View root = layoutInflater.inflate(R.layout.hero_item_layout, parent, false);
        return new HeroesAdapter.HeroViewHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        configureViewHolder((HeroesAdapter.HeroViewHolder) holder,position);

    }



    @Override
    public int getItemCount() {
        return apiGetHeroesResponse.getSuperheroes().size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    private static class HeroViewHolder extends RecyclerView.ViewHolder {




        private View root;
        private CircleImageView photo;
        private TextView name;


        public HeroViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            photo = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.name);



        }
    }




    private void configureViewHolder(HeroesAdapter.HeroViewHolder holder, int position){

        final Hero hero = apiGetHeroesResponse.getSuperheroes().get(position);

        if(hero != null){

            if(hero.getPhoto()!=null){

                Glide.with(holder.root.getContext()).load(hero.getPhoto()).into(holder.photo);
            }

            holder.name.setText(hero.getName());

            holder.root.setOnClickListener(v -> {
                if(heroSelectedInterface!=null){
                    heroSelectedInterface.onClickedHero(hero);
                }
            });

        }



    }


}