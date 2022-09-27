package recipe_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import recipe_application.application.data.service.*;
import recipe_application.application.model.*;
import recipe_application.application.model.measurement.Measurement;

import java.util.Arrays;
import java.util.List;


@Profile("!test")
@Transactional
@Component
public class CommandLineRunnerComponent implements CommandLineRunner {

    private final IngredientService ingredientService;
    private final RecipeCategoryService recipeCategoryService;
    private final RecipeIngredientService recipeIngredientService;
    private final RecipeInstructionService recipeInstructionService;
    private final RecipeService recipeService;

    @Autowired
    public CommandLineRunnerComponent(IngredientService ingredientService,
                                      RecipeCategoryService recipeCategoryService,
                                      RecipeService recipeService,
                                      RecipeIngredientService recipeIngredientService,
                                      RecipeInstructionService recipeInstructionService) {
        this.ingredientService = ingredientService;
        this.recipeCategoryService = recipeCategoryService;
        this.recipeIngredientService = recipeIngredientService;
        this.recipeInstructionService = recipeInstructionService;
        this.recipeService = recipeService;
    }

    private List<Ingredient> ingredientList(){
        return Arrays.asList(
                new Ingredient("Tomato"),
                new Ingredient("Chili Pepper"),
                new Ingredient("vegetable oil"),
                new Ingredient("Onion"),
                new Ingredient("Shrimps"),
                new Ingredient("Chicken"),
                new Ingredient("Seasoning"),
                new Ingredient("Salt"),
                new Ingredient("Rice"),
                new Ingredient("Potato")
        );
    }

    private List<RecipeCategory> recipeCategoryList(){
        return Arrays.asList(
                new RecipeCategory("Home"),
                new RecipeCategory("Celebration"),
                new RecipeCategory("Weekend"),
                new RecipeCategory("Chicken")
        );
    }

    private List<RecipeInstruction> recipeInstructionList(){
        return Arrays.asList(
                new RecipeInstruction("Blend the tomato, pepper and part onion with a blender."),
                new RecipeInstruction("Steam the mix until it is 1/3 its size."),
                new RecipeInstruction("Fry the other part of the onion in the vegetable oil until its golden."),
                new RecipeInstruction("Mix it with the steamed tomato, pepper and Onion mixture ."),
                new RecipeInstruction("Add seasoning and Salt."),
                new RecipeInstruction("Cook the mixture for 5 to 10 minutes."),
                new RecipeInstruction("Allow it to cool down."),
                new RecipeInstruction("Cook the rice with salt and water."),
                new RecipeInstruction("Mix with tomato stew."),
                new RecipeInstruction("Cook the mixture for a few minutes."),
                new RecipeInstruction("Serve cooked, baked of fried potato with the tomato stew.")
        );
    }


    @Override
    public void run(String... args) throws Exception {

        List<Ingredient> savedIngredientList = ingredientService.saveAll(ingredientList());
        List<RecipeCategory> savedRecipeCategoryList = recipeCategoryService.saveAll(recipeCategoryList());
        List<RecipeInstruction> savedRecipeInstructionList = recipeInstructionService.saveAll(recipeInstructionList());


        List<RecipeIngredient> recipeIngredientList = Arrays.asList(
                new RecipeIngredient(400, Measurement.G, savedIngredientList.get(0)),
                new RecipeIngredient(3, Measurement.G, savedIngredientList.get(1)),
                new RecipeIngredient(40, Measurement.DL, savedIngredientList.get(2)),
                new RecipeIngredient(100, Measurement.G, savedIngredientList.get(3)),
                new RecipeIngredient(800, Measurement.G, savedIngredientList.get(4)),
                new RecipeIngredient(1, Measurement.KG, savedIngredientList.get(5)),
                new RecipeIngredient(1, Measurement.G, savedIngredientList.get(6)),
                new RecipeIngredient(1, Measurement.TBSP, savedIngredientList.get(7))
        );
        List<RecipeIngredient> savedRecipeIngredientList = recipeIngredientService.saveAll(recipeIngredientList);


        List<Recipe> recipeList = Arrays.asList(
                new Recipe("Tomato stew recipe", savedRecipeInstructionList.get(0), savedRecipeIngredientList),
                new Recipe("Jollof rice recipe", savedRecipeInstructionList.get(1)),
                new Recipe("Potato sauce recipe", savedRecipeInstructionList.get(2))
        );
        List<Recipe> savedRecipeList = recipeService.saveAll(recipeList);


        savedRecipeList.get(0).addCategory(savedRecipeCategoryList.get(0));
        savedRecipeList.get(0).addCategory(savedRecipeCategoryList.get(1));
        savedRecipeList.get(0).addCategory(savedRecipeCategoryList.get(2));
        savedRecipeList.get(0).addCategory(savedRecipeCategoryList.get(3));

        savedRecipeIngredientList.get(0).setRecipe(savedRecipeList.get(0));
        savedRecipeIngredientList.get(1).setRecipe(savedRecipeList.get(0));
        savedRecipeIngredientList.get(2).setRecipe(savedRecipeList.get(0));
        savedRecipeIngredientList.get(3).setRecipe(savedRecipeList.get(0));
        savedRecipeIngredientList.get(4).setRecipe(savedRecipeList.get(0));
        savedRecipeIngredientList.get(5).setRecipe(savedRecipeList.get(0));
        savedRecipeIngredientList.get(6).setRecipe(savedRecipeList.get(0));
        savedRecipeIngredientList.get(7).setRecipe(savedRecipeList.get(0));

    }
}
