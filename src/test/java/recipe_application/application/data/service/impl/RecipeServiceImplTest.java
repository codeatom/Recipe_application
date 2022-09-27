package recipe_application.application.data.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import recipe_application.application.data.service.*;
import recipe_application.application.model.*;
import recipe_application.application.model.measurement.Measurement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RecipeServiceImplTest {

    @Autowired
    RecipeService recipeService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    RecipeInstructionService recipeInstructionService;
    @Autowired
    RecipeIngredientService recipeIngredientService;
    @Autowired
    RecipeCategoryService recipeCategoryService;


    List<Ingredient> ingredientList;
    List<RecipeInstruction> recipeInstructionList;
    List<RecipeCategory> recipeCategoryList;
    List<Recipe> recipeList;
    List<RecipeIngredient> recipeIngredientList;


    List<Ingredient> ingredientList(){
        return Arrays.asList(
                new Ingredient("Rice"),
                new Ingredient("Potato"),
                new Ingredient("Tomato"),
                new Ingredient("Salt Rice")
        );
    }

    List<RecipeCategory> recipeCategoryList(){
        return Arrays.asList(
                new RecipeCategory("Vegan"),
                new RecipeCategory("Celebration"),
                new RecipeCategory("Weekend"),
                new RecipeCategory("Chicken")
        );
    }

    List<RecipeInstruction> recipeInstructionList(){
        return Arrays.asList(
                new RecipeInstruction("Blend with a blender"),
                new RecipeInstruction("Add water, mix ingredients"),
                new RecipeInstruction("Add flavouring and cook for some minutes")
        );
    }

    List<Recipe> recipeList(List<RecipeInstruction> instructions){
        return Arrays.asList(
                new Recipe("Tomato stew recipe", instructions.get(0)),
                new Recipe("Baked potato recipe", instructions.get(1)),
                new Recipe("Fruit rice recipe", instructions.get(2)),
                new Recipe("Chocolate cake recipe", instructions.get(2))
        );
    }

    List<RecipeIngredient> recipeIngredientList(List<Ingredient> ingredientList, List<Recipe> recipeList){
        return Arrays.asList(
                new RecipeIngredient(5.0, Measurement.KG, ingredientList.get(0), recipeList.get(0)),
                new RecipeIngredient(3.3, Measurement.TBSP, ingredientList.get(1), recipeList.get(1)),
                new RecipeIngredient(40, Measurement.CL, ingredientList.get(2), recipeList.get(2)),
                new RecipeIngredient(40, Measurement.KG, ingredientList.get(3), recipeList.get(2))
        );
    }


    @BeforeEach
    void beforeEach() {
        ingredientList = ingredientService.saveAll(ingredientList());
        recipeInstructionList = recipeInstructionService.saveAll(recipeInstructionList());
        recipeCategoryList = recipeCategoryService.saveAll(recipeCategoryList());
        recipeList = recipeService.saveAll(recipeList(recipeInstructionList));
        recipeIngredientList = recipeIngredientService.saveAll(recipeIngredientList(ingredientList, recipeList));

        recipeList.get(0).addCategory(recipeCategoryList.get(0));
        recipeList.get(0).addCategory(recipeCategoryList.get(1));
        recipeList.get(1).addCategory(recipeCategoryList.get(0));
        recipeList.get(1).addCategory(recipeCategoryList.get(1));
        recipeList.get(1).addCategory(recipeCategoryList.get(2));
        recipeList.get(2).addCategory(recipeCategoryList.get(0));
        recipeList.get(2).addCategory(recipeCategoryList.get(3));
    }



    @Test
    void save() {
        //Assert
        assertEquals(1, recipeList.get(0).getId());
        assertEquals(2, recipeList.get(1).getId());
        assertEquals(3, recipeList.get(2).getId());
        assertEquals(4, recipeList.get(3).getId());
    }

    @Test
    void saveAll() {
        //Assert
        assertEquals(4, recipeList.size());
    }

    @Test
    void findById() {
        //Act
        Recipe actual = recipeService.findById(3).get();

        //Assert
        assertEquals(3, actual.getId());
    }

    @Test
    void findAll() {
        //Act
        List<Recipe> actual = (List<Recipe>) recipeService.findAll();

        //Assert
        assertEquals(recipeList.size(), actual.size());
    }

    @Test
    void findAllByInstructionId() {
        //Act
        List<Recipe> actual = recipeService.findAllByInstructionId(3);

        List<Recipe> all = (List<Recipe>) recipeService.findAll();
        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(2, actual.size());
    }

    @Test
    void findAllByCategoriesCategory() {
        //Act
        List<Recipe> actual = recipeService.findAllByCategoriesCategory(recipeCategoryList().get(1).getCategory());

        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(2, actual.size());
    }

    @Test
    void findAllByRecipeNameContainingIgnoreCase() {
        //Act
        List<Recipe> actual = recipeService.findAllByRecipeNameContainingIgnoreCase("potato");

        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(1, actual.size());
    }

    @Test
    void findDistinctByCategoriesCategoryIn() {
        //Arrange
        List<String> categories = new ArrayList<>();
        categories.add(recipeCategoryList().get(0).getCategory());
        categories.add(recipeCategoryList().get(1).getCategory());

        //Act
        List<Recipe> actual = recipeService.findDistinctByCategoriesCategoryIn(categories);

        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(3, actual.size());
    }

    @Test
    void findAllByRecipeIngredientsIngredientIngredientName() {
        //Act
        List<Recipe> actual = recipeService.findAllByRecipeIngredientsIngredientIngredientName(ingredientList().get(3).getIngredientName());

        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(1, actual.size());
    }

    @Test
    void update() {
        //Arrange
        String name = "new_String";

        //Act
        Recipe actual = recipeService.findById(4).get();
        actual.setRecipeName(name);

        //Assert
        assertEquals(name, actual.getRecipeName());
    }

    @Test
    void deleteById() {
        //Arrange
        List<Recipe> listBeforeDelete = (List<Recipe>) recipeService.findAll();

        //Act
        recipeService.deleteById(2);
        List<Recipe> listAfterDelete = (List<Recipe>) recipeService.findAll();

        //Assert
        assertEquals(listBeforeDelete.size()-1, listAfterDelete.size());
    }

    @Test
    void delete() {
        //Arrange
        List<Recipe> listBeforeDelete = (List<Recipe>) recipeService.findAll();

        //Act
        recipeService.delete(recipeService.findById(2).get());
        List<Recipe> listAfterDelete = (List<Recipe>) recipeService.findAll();

        //Assert
        assertEquals(listBeforeDelete.size()-1, listAfterDelete.size());
    }
}