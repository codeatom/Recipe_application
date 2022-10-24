package recipe_application.application.dto.views;

import recipe_application.application.model.RecipeCategory;
import recipe_application.application.model.RecipeIngredient;
import recipe_application.application.model.RecipeInstruction;

import java.util.*;


public class RecipeView {

    private final Integer id;
    private final String recipeName;
    private final RecipeInstruction instruction;
    private final List<RecipeIngredient> recipeIngredients;
    private final Set<RecipeCategory> categories;

    public RecipeView(Integer id, String recipeName, RecipeInstruction instruction, List<RecipeIngredient> recipeIngredients, Set<RecipeCategory> categories) {
        this.id = id;
        this.recipeName = recipeName;
        this.instruction = instruction;
        this.recipeIngredients = recipeIngredients;
        this.categories = categories;
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

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public Set<RecipeCategory> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeView that = (RecipeView) o;
        return Objects.equals(id, that.id) && Objects.equals(recipeName, that.recipeName) && Objects.equals(instruction, that.instruction) && Objects.equals(recipeIngredients, that.recipeIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeName, instruction, recipeIngredients);
    }
}
