package guru.springframework.services;

import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository RecipeRepository;

    public List<Recipe> findAll() {

        var it = RecipeRepository.findAll();

        var Recipes = new ArrayList<Recipe>();
        it.forEach(e -> Recipes.add(e));

        return Recipes;
    }

}