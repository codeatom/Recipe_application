package recipe_application.application.data.service;

import recipe_application.application.model.RecipeInstruction;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface RecipeInstructionService {

    RecipeInstruction save (RecipeInstruction recipeInstruction);

    List<RecipeInstruction> saveAll(List<RecipeInstruction> recipeInstructionList);

    Optional<RecipeInstruction> findById(Integer id);

    Collection<RecipeInstruction> findAll();

    RecipeInstruction update (RecipeInstruction recipeInstruction);

    boolean deleteById(Integer id);

    boolean  delete(RecipeInstruction recipeInstruction);
}
