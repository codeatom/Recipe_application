package recipe_application.application.data.repo;

import org.springframework.data.repository.CrudRepository;
import recipe_application.application.model.Ingredient;

import java.util.List;
import java.util.Optional;


public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    Optional<Ingredient> findByIngredientNameIgnoreCase(String ingredientName);

    List<Ingredient> findByIngredientNameContainsIgnoreCase(String ingredientName);



    //-------------------------------Using jpql query-------------------------------//
//    @Query("SELECT i FROM Ingredient i WHERE LOWER(i.ingredientName) = LOWER(:name)")
//    Optional<Ingredient> findByIngredientName(@Param("name")String ingredientName);
//
//    @Query("SELECT i FROM Ingredient i WHERE LOWER(i.ingredientName) LIKE LOWER (CONCAT('%',:name,'%'))")
//    List<Ingredient> findAllByIngredientName(@Param("name")String ingredientName);

}
