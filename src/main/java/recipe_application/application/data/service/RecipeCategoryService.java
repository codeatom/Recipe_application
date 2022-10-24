package recipe_application.application.data.service;

import recipe_application.application.dto.forms.recipeCategoryForm.CreateRecipeCategoryForm;
import recipe_application.application.dto.forms.recipeCategoryForm.UpdateRecipeCategoryForm;
import recipe_application.application.dto.views.RecipeCategoryView;
import recipe_application.application.model.RecipeCategory;

import java.util.Collection;


public interface RecipeCategoryService {

    RecipeCategoryView save(CreateRecipeCategoryForm createRecipeCategoryForm);

    RecipeCategoryView findById(Integer id);

    Collection<RecipeCategoryView> findAll();

    RecipeCategoryView update(UpdateRecipeCategoryForm updateRecipeCategoryForm);

    boolean deleteById(Integer id);

    boolean delete(RecipeCategory recipeCategory);
}