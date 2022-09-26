package recipe_application.application.data.repo;

import org.springframework.data.repository.CrudRepository;
import recipe_application.application.model.Recipe;

import java.util.Collection;
import java.util.List;


public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    List<Recipe> findAllByInstructionId(Integer id);

    List<Recipe> findAllByCategoriesCategory(String category);

    List<Recipe> findAllByRecipeNameContainingIgnoreCase(String recipeName);

    List<Recipe> findDistinctByCategoriesCategoryIn(Collection<String> categoryList);

    List<Recipe> findAllByRecipeIngredientsIngredientIngredientName(String recipeName);



      //-------------------------------Using jpql query-------------------------------//
//    @Query("SELECT r FROM Recipe r JOIN FETCH r.instruction ri WHERE ri.id = :id")
//    List<Recipe> findByInstructionId(@Param("name")Integer id);
//
//    @Query("SELECT r FROM Recipe r JOIN FETCH r.categories rc WHERE rc.category = :name")
//    List<Recipe> findByCategory(@Param("name")String category);
//
//    @Query("SELECT r FROM Recipe r WHERE LOWER(r.recipeName) LIKE LOWER (CONCAT('%',:name,'%'))")
//    List<Recipe> findByRecipeName(@Param("name")String recipeName);
//
//    @Query("SELECT DISTINCT r FROM Recipe r JOIN FETCH r.categories rc WHERE rc.category IN :categoryList")
//    List<Recipe> findByCategoryList(@Param("categoryList")Collection<String> categoryList);
//
//    @Query("SELECT r FROM Recipe r JOIN FETCH r.recipeIngredients rr JOIN FETCH rr.ingredient rri WHERE rri.ingredientName = :name")
//    List<Recipe> findByIngredientName(@Param("name")String ingredientName);

}
