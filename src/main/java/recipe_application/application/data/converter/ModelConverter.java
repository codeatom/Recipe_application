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
        IngredientView ingredientView = ingredientToView(entity.getIngredient());
        RecipeView recipeView = new RecipeView(entity.getRecipe().getId(), entity.getRecipe().getRecipeName(), entity.getRecipe().getInstruction());

        return new RecipeIngredientView(entity.getId(), entity.getAmount(), entity.getMeasurement(), ingredientView, recipeView);
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
        Set<RecipeView> recipeViewSet = new HashSet<>();

        for (Recipe recipe : entity.getRecipes()){
            RecipeView recipeView = new RecipeView(recipe.getId(), recipe.getRecipeName(), recipe.getInstruction());
            recipeViewSet.add(recipeView);
        }

        return new RecipeCategoryView(entity.getId(), entity.getCategory(), recipeViewSet);
    }

    @Override
    public Collection<RecipeCategoryView> recipeCategoryListToViewList(Collection<RecipeCategory> entities) {
        Collection<RecipeCategoryView> recipeCategoryViews = new HashSet<>();

        for(RecipeCategory recipeCategory : entities){
            recipeCategoryViews.add(recipeCategoryToView(recipeCategory));
        }

        return recipeCategoryViews;
    }

    @Override
    public RecipeView recipeToView(Recipe entity) {
        List<RecipeIngredientView> recipeIngredientViews = new ArrayList<>();
        Set<RecipeCategoryView> recipeCategoryViews = new HashSet<>();

        for (RecipeIngredient recipeIngredient : entity.getRecipeIngredients()){
            RecipeIngredientView recipeIngredientView = new RecipeIngredientView(recipeIngredient.getId(), recipeIngredient.getAmount(), recipeIngredient.getMeasurement());
            recipeIngredientViews.add(recipeIngredientView);
        }

        for (RecipeCategory recipeCategory : entity.getCategories()){
            RecipeCategoryView recipeCategoryView = new RecipeCategoryView(recipeCategory.getId(), recipeCategory.getCategory());
            recipeCategoryViews.add(recipeCategoryView);
        }

        return new RecipeView(entity.getId(), entity.getRecipeName(), entity.getInstruction(), recipeIngredientViews, recipeCategoryViews);
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
