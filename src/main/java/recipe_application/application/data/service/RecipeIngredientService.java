package recipe_application.application.data.service;

import recipe_application.application.dto.forms.recipeIngredientForm.AddRecipeForm;
import recipe_application.application.dto.forms.recipeIngredientForm.CreateRecipeIngredientForm;
import recipe_application.application.dto.forms.recipeIngredientForm.UpdateRecipeIngredientForm;
import recipe_application.application.dto.views.RecipeIngredientView;
import recipe_application.application.model.RecipeIngredient;

import java.util.Collection;
import java.util.List;


public interface RecipeIngredientService {

    RecipeIngredientView save(CreateRecipeIngredientForm recipeIngredientForm);

    RecipeIngredientView findById(Integer id);

    Collection<RecipeIngredientView> findAll();

    List<RecipeIngredientView> findAllByIngredientId(Integer id);

    RecipeIngredientView update(UpdateRecipeIngredientForm updateRecipeIngredientForm);

    boolean deleteById(Integer id);

    RecipeIngredientView addRecipe(AddRecipeForm addRecipeForm);

    void removeRecipe(Integer id);

    boolean  delete(RecipeIngredient recipeIngredient);
}