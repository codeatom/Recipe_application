package recipe_application.application.dto.forms.recipeForm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddRecipeIngredientForm {

    @NotNull
    @Positive
    private Integer recipeId;

    @NotNull
    @Positive
    private Integer recipeIngredientId;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(Integer recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }
}
