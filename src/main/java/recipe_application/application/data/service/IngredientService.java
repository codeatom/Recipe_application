package recipe_application.application.data.service;

import recipe_application.application.dto.forms.ingredientForm.CreateIngredientForm;
import recipe_application.application.dto.forms.ingredientForm.UpdateIngredientForm;
import recipe_application.application.dto.views.IngredientView;
import recipe_application.application.model.Ingredient;

import java.util.Collection;
import java.util.List;


public interface IngredientService {

    IngredientView save (CreateIngredientForm createIngredientForm);

    IngredientView findById(Integer id);

    Collection<IngredientView> findAll();

    IngredientView findByIngredientNameIgnoreCase(String ingredientName);

    List<IngredientView> findByIngredientNameContainsIgnoreCase(String ingredientName);

    IngredientView update(UpdateIngredientForm updateIngredientForm);

    boolean deleteById(Integer id);

    boolean delete(Ingredient ingredient);
}