package recipe_application.application.data.service;

import recipe_application.application.dto.forms.recipeInstructionForm.CreateRecipeInstructionForm;
import recipe_application.application.dto.forms.recipeInstructionForm.UpdateRecipeInstructionForm;
import recipe_application.application.dto.views.RecipeInstructionView;
import recipe_application.application.model.RecipeInstruction;

import java.util.Collection;


public interface RecipeInstructionService {

    RecipeInstructionView save(CreateRecipeInstructionForm createRecipeInstructionForm);

    RecipeInstructionView findById(Integer id);

    Collection<RecipeInstructionView> findAll();

    RecipeInstructionView update(UpdateRecipeInstructionForm updateRecipeInstructionForm);

    boolean deleteById(Integer id);

    boolean  delete(RecipeInstruction recipeInstruction);
}

