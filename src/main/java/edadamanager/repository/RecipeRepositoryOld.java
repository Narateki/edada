package edadamanager.repository;

import edadamanager.model.*;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class RecipeRepositoryOld {

    private List<Recipe> source;

    public RecipeRepositoryOld() {
        this.source = new LinkedList(Arrays.asList());
    }


    public List<Ingredient> getIngredients() {
        return new LinkedList(Arrays.asList("tomato", "potato", "chiken", "fish", "milk"));

    }
    public List<String> getInventories() {
        return new LinkedList(Arrays.asList("pan", "pot", "skewer"));
    }

//    public List<Recipe> findByIngredient(String name) {
//        List<Recipe> result = new LinkedList<Recipe>();
//        for (Recipe r : this.source) {
//            if (r.getIngredients().equals(name) ){
//                result.add(r);
//            }
//        }
//        return result;
//    }

    public Recipe findByInventory(String inventory) {
        for (Recipe r : this.source) {
            if(r.getInventories().equals(inventory))
                return r;
        }
        return null;
    }

    public Recipe findByName(String name) {
        for (Recipe r: this.source) {
            if(r.getName().equals(name))
                return r;
        }
        return null;
    }

}
