package recipe_application.application.data.service;

import recipe_application.application.model.Recipe;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface RecipeService {

    Recipe save (Recipe recipe);

    List<Recipe> saveAll(List<Recipe> recipeList);

    Optional<Recipe> findById(Integer id);

    Collection<Recipe> findAll();

    Recipe update (Recipe recipe);

    boolean deleteById(Integer id);

    boolean  delete(Recipe recipe);

    List<Recipe> findAllByInstructionId(Integer id);

    List<Recipe> findAllByCategoriesCategory(String category);

    List<Recipe> findAllByRecipeNameContainingIgnoreCase(String recipeName);

    List<Recipe> findDistinctByCategoriesCategoryIn(Collection<String> categories);

    List<Recipe> findAllByRecipeIngredientsIngredientIngredientName(String ingredientName);
}
