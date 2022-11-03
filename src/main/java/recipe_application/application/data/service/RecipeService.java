package recipe_application.application.data.service;

import recipe_application.application.dto.forms.recipeForm.AddRecipeCategoryForm;
import recipe_application.application.dto.forms.recipeForm.AddRecipeIngredientForm;
import recipe_application.application.dto.forms.recipeForm.CreateRecipeForm;
import recipe_application.application.dto.forms.recipeForm.UpdateRecipeForm;
import recipe_application.application.dto.views.RecipeView;
import recipe_application.application.model.Recipe;

import java.util.Collection;
import java.util.List;


public interface RecipeService {

    RecipeView save(CreateRecipeForm createRecipeForm);

    RecipeView findById(Integer id);

    Collection<RecipeView> findAll();

    RecipeView update (UpdateRecipeForm updateRecipeForm);

    boolean deleteById(Integer id);

    boolean  delete(Recipe recipe);

    List<RecipeView> findAllByInstructionId(Integer id);

    List<RecipeView> findAllByCategoriesCategory(String category);

    List<RecipeView> findAllByRecipeNameContainingIgnoreCase(String recipeName);

    List<RecipeView> findDistinctByCategoriesCategoryIn(Collection<String> categories);

    List<RecipeView> findAllByRecipeIngredientsIngredientIngredientName(String ingredientName);

    RecipeView addRecipeIngredient(AddRecipeIngredientForm addRecipeIngredientForm);

    void removeRecipeIngredient(Integer recipeId, Integer recipeIngredientId);

    RecipeView addRecipeCategory(AddRecipeCategoryForm addRecipeCategoryForm);

    void removeRecipeCategory(Integer recipeId, Integer recipeCategoryId);
}
