package recipe_application.application.dto.forms.recipeForm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddRecipeCategoryForm {

    @NotNull
    @Positive
    private Integer id;

    @NotNull
    @Positive
    private Integer recipeCategoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecipeCategoryId() {
        return recipeCategoryId;
    }

    public void setRecipeCategoryId(Integer recipeCategoryId) {
        this.recipeCategoryId = recipeCategoryId;
    }
}
