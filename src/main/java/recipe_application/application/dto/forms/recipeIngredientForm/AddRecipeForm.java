package recipe_application.application.dto.forms.recipeIngredientForm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddRecipeForm {

    @NotNull
    @Positive
    private Integer id;

    @NotNull
    @Positive
    private Integer recipeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }
}
