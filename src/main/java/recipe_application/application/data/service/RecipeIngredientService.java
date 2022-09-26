package recipe_application.application.data.service;

import recipe_application.application.model.RecipeIngredient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface RecipeIngredientService {

    RecipeIngredient save (RecipeIngredient recipeIngredient);

    List<RecipeIngredient> saveAll(List<RecipeIngredient> recipeIngredientList);

    Optional<RecipeIngredient> findById(Integer id);

    Collection<RecipeIngredient> findAll();

    List<RecipeIngredient> findAllByIngredientId(Integer id);

    RecipeIngredient update (RecipeIngredient recipeIngredient);

    boolean deleteById(Integer id);

    boolean  delete(RecipeIngredient recipeIngredient);
}
