package recipe_application.application.dto.forms.ingredientForm;

import org.springframework.stereotype.Component;
import recipe_application.application.util.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Component
public class CreateIngredientForm implements Serializable {

    @Null
    private Integer id;

    @NotBlank(message = ValidationMessages.IS_REQUIRED)
    @Size(min = 2, max = 50, message = ValidationMessages.BETWEEN_2_AND_50_LETTERS)
    private String ingredientName;

    public CreateIngredientForm() {
    }

    public CreateIngredientForm(Integer id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
