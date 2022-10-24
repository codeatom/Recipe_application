package recipe_application.application.data.converter;

import org.springframework.stereotype.Component;
import recipe_application.application.dto.views.*;
import recipe_application.application.model.*;

import java.util.*;

@Component
public class ModelConverter implements Converter{

    @Override
    public IngredientView ingredientToView(Ingredient entity) {
        return new IngredientView(entity.getId(), entity.getIngredientName());
    }

    @Override
    public Collection<IngredientView> ingredientListToViewList(Collection<Ingredient> entities) {
        Collection<IngredientView> ingredientViews = new ArrayList<>();

        for(Ingredient ingredient : entities){
            ingredientViews.add(ingredientToView(ingredient));
        }

        return ingredientViews;
    }

    @Override
    public RecipeIngredientView recipeIngredientToView(RecipeIngredient entity) {
        return new RecipeIngredientView(entity.getId(), entity.getAmount(), entity.getMeasurement(), entity.getIngredient(), entity.getRecipe());
    }

    @Override
    public Collection<RecipeIngredientView> recipeIngredientListToViewList(Collection<RecipeIngredient> entities) {
        Collection<RecipeIngredientView> recipeIngredientViews = new ArrayList<>();

        for(RecipeIngredient recipeIngredient : entities){
            recipeIngredientViews.add(recipeIngredientToView(recipeIngredient));
        }

        return recipeIngredientViews;
    }

    @Override
    public RecipeInstructionView recipeInstructionToView(RecipeInstruction entity) {
        return new RecipeInstructionView(entity.getId(), entity.getTitle(), entity.getInstruction());
    }

    @Override
    public Collection<RecipeInstructionView> recipeInstructionListToViewList(Collection<RecipeInstruction> entities) {
        Collection<RecipeInstructionView> recipeInstructionViews = new ArrayList<>();

        for(RecipeInstruction recipeInstruction : entities){
            recipeInstructionViews.add(recipeInstructionToView(recipeInstruction));
        }

        return recipeInstructionViews;
    }

    @Override
    public RecipeCategoryView recipeCategoryToView(RecipeCategory entity) {
        return new RecipeCategoryView(entity.getId(), entity.getCategory(), entity.getRecipes());
    }

    @Override
    public Collection<RecipeCategoryView> recipeCategoryListToViewList(Collection<RecipeCategory> entities) {
        Collection<RecipeCategoryView> recipeCategoryViews = new ArrayList<>();

        for(RecipeCategory recipeCategory : entities){
            recipeCategoryViews.add(recipeCategoryToView(recipeCategory));
        }

        return recipeCategoryViews;
    }

    @Override
    public RecipeView recipeToView(Recipe entity) {
        return new RecipeView(entity.getId(), entity.getRecipeName(), entity.getInstruction(), entity.getRecipeIngredients(), entity.getCategories());
    }

    @Override
    public Collection<RecipeView> recipeListToViewList(Collection<Recipe> entities) {
        Collection<RecipeView> recipeViews = new ArrayList<>();

        for(Recipe recipe : entities){
            recipeViews.add(recipeToView(recipe));
        }

        return recipeViews;
    }
}
