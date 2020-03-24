package guru.springframework;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@SpringBootApplication
public class Spring5RecipeAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Spring5RecipeAppApplication.class, args);

		CategoryRepository categoryRepository = (CategoryRepository)context.getBean("categoryRepository");
		UnitOfMeasureRepository unitOfMeasureRepository = (UnitOfMeasureRepository)context.getBean("unitOfMeasureRepository");
		
		Optional<Category> categoryOptional = categoryRepository.findByDescription("Mexican");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("");
		
        System.out.println("Cat Id is: " + categoryOptional.get().getId());
        System.out.println("UOM ID is: " + unitOfMeasureOptional.get().getId());
		
		RecipeRepository recipeRepository = (RecipeRepository)context.getBean("recipeRepository");
		Recipe guacamole = new Recipe();
		guacamole.setDescription("Guacamole");
		guacamole.setCategories(new HashSet<Category>(Arrays.asList(categoryOptional.get())));
		guacamole.setCookTime(0);
		guacamole.setDifficulty(Difficulty.EASY);
		guacamole.setDirections("Just mash avocado and salt it.");

		Ingredient avocado = new Ingredient();
		avocado.setAmount(new BigDecimal(2.0));
		avocado.setDescription("Avocado");
		avocado.setRecipe(guacamole);
		avocado.setUom(unitOfMeasureOptional.get());
		Ingredient salt = new Ingredient();
		salt.setAmount(new BigDecimal(1.0));
		salt.setDescription("Salt");
		salt.setRecipe(guacamole);
		salt.setUom(unitOfMeasureRepository.findByDescription("Dash").get());
		guacamole.setIngredients(new HashSet<Ingredient>(Arrays.asList(avocado)));

		recipeRepository.save(guacamole);


	}
}
