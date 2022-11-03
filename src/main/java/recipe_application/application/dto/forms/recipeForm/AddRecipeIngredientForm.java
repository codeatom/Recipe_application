package recipe_application.application.dto.forms.recipeForm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddRecipeIngredientForm {

    @NotNull
    @Positive
    private Integer id;

    @NotNull
    @Positive
    private Integer recipeIngredientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(Integer recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }
}
