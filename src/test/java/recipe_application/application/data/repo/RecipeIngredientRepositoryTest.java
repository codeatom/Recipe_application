package recipe_application.application.data.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import recipe_application.application.model.*;
import recipe_application.application.model.measurement.Measurement;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RecipeIngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;


    public List<Ingredient> ingredientList(){
        return Arrays.asList(
                new Ingredient("Rice"),
                new Ingredient("Potato"),
                new Ingredient("Tomato"),
                new Ingredient("Blended Tomato")
        );
    }

    public List<RecipeIngredient> recipeIngredientList(List<Ingredient> ingredientList){
        return Arrays.asList(
                new RecipeIngredient( 5.0, Measurement.KG, ingredientList.get(0),null),
                new RecipeIngredient( 3.3, Measurement.TBSP, ingredientList.get(1),null),
                new RecipeIngredient( 40, Measurement.CL, ingredientList.get(2),null),
                new RecipeIngredient( 40, Measurement.KG, ingredientList.get(3),null)
        );
    }

    @BeforeEach
    void beforeEach() {
        List<Ingredient> ingredientList = ingredientList();
        List<RecipeIngredient> recipeIngredientList = recipeIngredientList(ingredientList);

        ingredientList = (List<Ingredient>) ingredientRepository.saveAll(ingredientList);
        recipeIngredientList = (List<RecipeIngredient>) recipeIngredientRepository.saveAll(recipeIngredientList);

    }

    @Test
    void findAllByIngredientId() {
        //Act
        List<RecipeIngredient> actual = recipeIngredientRepository.findAllByIngredientId(3);

        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(1, actual.size());
    }
}