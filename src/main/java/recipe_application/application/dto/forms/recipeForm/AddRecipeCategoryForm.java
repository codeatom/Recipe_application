package recipe_application.application.dto.forms.recipeForm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddRecipeCategoryForm {

    @NotNull
    @Positive
    private Integer recipeId;

    @NotNull
    @Positive
    private Integer recipeCategoryId;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getRecipeCategoryId() {
        return recipeCategoryId;
    }

    public void setRecipeCategoryId(Integer recipeCategoryId) {
        this.recipeCategoryId = recipeCategoryId;
    }
}
