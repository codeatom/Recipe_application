package recipe_application.application.dto.forms.recipeCategoryForm;

import org.springframework.stereotype.Component;
import recipe_application.application.util.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;


@Component
public class CreateRecipeCategoryForm {

    @Null
    private Integer id;

    @NotBlank(message = ValidationMessages.IS_REQUIRED)
    @Size(min = 2, max = 50, message = ValidationMessages.BETWEEN_2_AND_50_LETTERS)
    private String category;

    public CreateRecipeCategoryForm() {
    }

    public CreateRecipeCategoryForm(Integer id, String category) {
        this.id = id;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
