package recipe_application.application.data.service;

import recipe_application.application.model.RecipeCategory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface RecipeCategoryService {

    RecipeCategory save (RecipeCategory recipeCategory);

    List<RecipeCategory> saveAll(List<RecipeCategory> recipeCategoryList);

    Optional<RecipeCategory> findById(Integer id);

    Collection<RecipeCategory> findAll();

    RecipeCategory update (RecipeCategory recipeCategory);

    boolean deleteById(Integer id);

    boolean delete(RecipeCategory recipeCategory);
}
