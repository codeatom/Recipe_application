package recipe_application.application.data.repo;

import org.springframework.data.repository.CrudRepository;
import recipe_application.application.model.RecipeIngredient;

import java.util.List;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer> {

    List<RecipeIngredient> findAllByIngredientId(Integer id);



    //-------------------------------Using jpql query-------------------------------//
//    @Query("SELECT r FROM RecipeIngredient r JOIN FETCH r.ingredient ri WHERE ri.id = :id")
//    List<Recipe> findByIngredientId(@Param("name")Integer id);

}
