package recipe_application.application.data.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import recipe_application.application.model.*;
import recipe_application.application.model.measurement.Measurement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    RecipeInstructionRepository recipeInstructionRepository;
    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;
    @Autowired
    RecipeCategoryRepository recipeCategoryRepository;


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
        List<Ingredient> ingredientList = (List<Ingredient>) ingredientRepository.saveAll(ingredientList());
        List<RecipeInstruction> recipeInstructionList = (List<RecipeInstruction>) recipeInstructionRepository.saveAll(recipeInstructionList());
        List<RecipeCategory> recipeCategoryList = (List<RecipeCategory>) recipeCategoryRepository.saveAll(recipeCategoryList());
        List<Recipe> recipeList = (List<Recipe>) recipeRepository.saveAll(recipeList(recipeInstructionList));
        List<RecipeIngredient> recipeIngredientList = (List<RecipeIngredient>) recipeIngredientRepository.saveAll(recipeIngredientList(ingredientList, recipeList));

        recipeList.get(0).addCategory(recipeCategoryList.get(0));
        recipeList.get(0).addCategory(recipeCategoryList.get(1));
        recipeList.get(1).addCategory(recipeCategoryList.get(0));
        recipeList.get(1).addCategory(recipeCategoryList.get(1));
        recipeList.get(1).addCategory(recipeCategoryList.get(2));
        recipeList.get(2).addCategory(recipeCategoryList.get(0));
        recipeList.get(2).addCategory(recipeCategoryList.get(3));
    }

    @Test
    void findAllByInstructionId() {
        //Act
        List<Recipe> actual = recipeRepository.findAllByInstructionId(3);

        List<Recipe> all = (List<Recipe>) recipeRepository.findAll();
        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(2, actual.size());
    }

    @Test
    void findAllByCategoriesCategory() {
        //Act
        List<Recipe> actual = recipeRepository.findAllByCategoriesCategory(recipeCategoryList().get(1).getCategory());

        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(2, actual.size());
    }

    @Test
    void findAllByRecipeNameContainingIgnoreCase() {
        //Act
        List<Recipe> actual = recipeRepository.findAllByRecipeNameContainingIgnoreCase("potato");

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
        List<Recipe> actual = recipeRepository.findDistinctByCategoriesCategoryIn(categories);

        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(3, actual.size());
    }

    @Test
    void findAllByRecipeIngredientsIngredientIngredientName() {
        //Act
        List<Recipe> actual = recipeRepository.findAllByRecipeIngredientsIngredientIngredientName(ingredientList().get(3).getIngredientName());

        //Assert
        assertTrue(actual.size() > 0);
        assertEquals(1, actual.size());
    }
}