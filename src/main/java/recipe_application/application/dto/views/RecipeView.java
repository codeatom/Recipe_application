package recipe_application.application.dto.views;

import recipe_application.application.model.RecipeInstruction;
import java.util.*;


public class RecipeView {

    private final Integer id;
    private final String recipeName;
    private final RecipeInstruction instruction;
    private List<RecipeIngredientView> recipeIngredientViews;
    private Set<RecipeCategoryView> recipeCategoryViews;

    public RecipeView(Integer id, String recipeName, RecipeInstruction instruction) {
        this.id = id;
        this.recipeName = recipeName;
        this.instruction = instruction;
    }

    public RecipeView(Integer id, String recipeName, RecipeInstruction instruction, List<RecipeIngredientView> recipeIngredientViews, Set<RecipeCategoryView> recipeCategoryViews) {
        this(id, recipeName, instruction);
        this.recipeIngredientViews = recipeIngredientViews;
        this.recipeCategoryViews = recipeCategoryViews;
    }

    public Integer getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public RecipeInstruction getInstruction() {
        return instruction;
    }

    public List<RecipeIngredientView> getRecipeIngredientViews() {
        return recipeIngredientViews;
    }

    public Set<RecipeCategoryView> getRecipeCategoryViews() {
        return recipeCategoryViews;
    }
}
