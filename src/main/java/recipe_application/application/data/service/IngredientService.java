package recipe_application.application.data.service;

import recipe_application.application.model.Ingredient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface IngredientService {

    Ingredient save (Ingredient ingredient);

    List<Ingredient> saveAll(List<Ingredient> ingredientList);

    Optional<Ingredient> findById(Integer id);

    Collection<Ingredient> findAll();

    Optional<Ingredient> findByIngredientNameIgnoreCase(String ingredientName);

    List<Ingredient> findByIngredientNameContainsIgnoreCase(String ingredientName);

    Ingredient update (Ingredient ingredient);

    boolean deleteById(Integer id);

    boolean delete(Ingredient ingredient);
}
