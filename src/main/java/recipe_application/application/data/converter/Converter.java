package recipe_application.application.data.converter;

import recipe_application.application.dto.views.*;
import recipe_application.application.model.*;

import java.util.Collection;


public interface Converter {

    IngredientView ingredientToView(Ingredient entity);
    Collection<IngredientView> ingredientListToViewList(Collection<Ingredient> entities);
    RecipeIngredientView recipeIngredientToView(RecipeIngredient entity);
    Collection<RecipeIngredientView> recipeIngredientListToViewList(Collection<RecipeIngredient> entities);
    RecipeInstructionView recipeInstructionToView(RecipeInstruction entity);
    Collection<RecipeInstructionView> recipeInstructionListToViewList(Collection<RecipeInstruction> entities);
    RecipeCategoryView recipeCategoryToView(RecipeCategory entity);
    Collection<RecipeCategoryView> recipeCategoryListToViewList(Collection<RecipeCategory> entities);
    RecipeView recipeToView(Recipe entity);
    Collection<RecipeView> recipeListToViewList(Collection<Recipe> entities);
}
