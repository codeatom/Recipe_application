package recipe_application.application.data.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import recipe_application.application.data.service.IngredientService;
import recipe_application.application.model.Ingredient;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class IngredientServiceImplTest {

    @Autowired
    IngredientService ingredientService;


    List<Ingredient> ingredientList;

    List<Ingredient> ingredientList(){
        return Arrays.asList(
                new Ingredient("Rice"),
                new Ingredient("Potato"),
                new Ingredient("Tomato"),
                new Ingredient("Blended Tomato")
        );
    }


    @BeforeEach
    void beforeEach() {
        ingredientList = ingredientService.saveAll(ingredientList());
    }

    @Test
    void save() {
        //Assert
        assertEquals(1, ingredientList.get(0).getId());
        assertEquals(2, ingredientList.get(1).getId());
        assertEquals(3, ingredientList.get(2).getId());
        assertEquals(4, ingredientList.get(3).getId());
    }

    @Test
    void findById() {
        //Act
        Ingredient actual = ingredientService.findById(3).get();

        //Assert
        assertEquals(3, actual.getId());
    }

    @Test
    void findAll() {
        //Act
        List<Ingredient> actual = (List<Ingredient>) ingredientService.findAll();

        //Assert
        assertEquals(ingredientList.size(), actual.size());
    }

    @Test
    void findByIngredientNameIgnoreCase() {
        //Act
        Ingredient actual = ingredientService.findByIngredientNameIgnoreCase("Rice").get();

        //Assert
        assertEquals(1, actual.getId());
    }

    @Test
    void findByIngredientNameContainsIgnoreCase() {
        //Act
        List<Ingredient> actual = ingredientService.findByIngredientNameContainsIgnoreCase("Tomato");

        //Assert
        assertEquals(2, actual.size());
    }

    @Test
    void update() {
        //Arrange
        String name = "new_String";

        //Act
        Ingredient actual = ingredientService.findById(4).get();
        actual.setIngredientName(name);

        //Assert
        assertEquals(name, actual.getIngredientName());
    }

    @Test
    void deleteById() {
        //Arrange
        List<Ingredient> listBeforeDelete = (List<Ingredient>) ingredientService.findAll();

        //Act
        ingredientService.deleteById(2);
        List<Ingredient> listAfterDelete = (List<Ingredient>) ingredientService.findAll();

        //Assert
        assertEquals(listAfterDelete.size(), listBeforeDelete.size()-1);
    }

    @Test
    void delete() {
        //Arrange
        List<Ingredient> listBeforeDelete = (List<Ingredient>) ingredientService.findAll();

        //Act
        ingredientService.delete(ingredientService.findById(2).get());
        List<Ingredient> listAfterDelete = (List<Ingredient>) ingredientService.findAll();

        //Assert
        assertEquals(listAfterDelete.size(), listBeforeDelete.size()-1);
    }
}