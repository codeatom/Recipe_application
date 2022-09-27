package recipe_application.application.data.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import recipe_application.application.model.Ingredient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    Ingredient ingredient_1;
    Ingredient ingredient_2;
    Ingredient ingredient_3;
    Ingredient ingredient_4;


    @BeforeEach
    void beforeEach() {
        ingredient_1 = new Ingredient("Rice");
        ingredient_2 = new Ingredient("Potato");
        ingredient_3 = new Ingredient("Tomato");
        ingredient_4 = new Ingredient("Blended Tomato");

        ingredientRepository.save(ingredient_1);
        ingredientRepository.save(ingredient_2);
        ingredientRepository.save(ingredient_3);
        ingredientRepository.save(ingredient_4);
    }


    @Test
    void findByIngredientNameIgnoreCase() {
        //Act
        Ingredient actual_1 = ingredientRepository.findByIngredientNameIgnoreCase(ingredient_1.getIngredientName()).get();
        Ingredient actual_2 = ingredientRepository.findByIngredientNameIgnoreCase(ingredient_2.getIngredientName()).get();
        Ingredient actual_3 = ingredientRepository.findByIngredientNameIgnoreCase(ingredient_3.getIngredientName()).get();

        //Assert
        assertEquals(ingredient_1, actual_1);
        assertEquals(ingredient_2, actual_2);
        assertEquals(ingredient_3, actual_3);
    }

    @Test
    void findByIngredientNameContainsIgnoreCase() {
        //Act
        List<Ingredient> actual = ingredientRepository.findByIngredientNameContainsIgnoreCase("toma");

        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(2, actual.size());
    }
}