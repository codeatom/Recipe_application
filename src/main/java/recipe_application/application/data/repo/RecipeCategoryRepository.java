package recipe_application.application.data.repo;

import org.springframework.data.repository.CrudRepository;
import recipe_application.application.model.RecipeCategory;

public interface RecipeCategoryRepository extends CrudRepository<RecipeCategory, Integer> {
}
