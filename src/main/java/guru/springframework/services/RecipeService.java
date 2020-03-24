package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> findAll() {

        Iterable<Recipe> it = recipeRepository.findAll();

        ArrayList<Recipe> Recipes = new ArrayList<>();
        it.forEach(e -> Recipes.add(e));

        return Recipes;
    }

}