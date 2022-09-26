package recipe_application.application.data.repo;

import org.springframework.data.repository.CrudRepository;
import recipe_application.application.model.RecipeInstruction;

public interface RecipeInstructionRepository extends CrudRepository<RecipeInstruction, Integer> {
}
