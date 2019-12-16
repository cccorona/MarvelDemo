package mx.com.marveldemo.model.apimodel;

import java.io.Serializable;
import java.util.ArrayList;

import mx.com.marveldemo.model.Hero;

public class ApiGetHeroesResponse implements Serializable {

    private ArrayList<Hero> superheroes;


    public ArrayList<Hero> getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(ArrayList<Hero> superheroes) {
        this.superheroes = superheroes;
    }
}
