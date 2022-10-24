package recipe_application.application.dto.forms.recipeInstructionForm;

import org.springframework.stereotype.Component;
import recipe_application.application.util.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;


@Component
public class CreateRecipeInstructionForm {

    @Null
    private Integer id;

    @NotBlank(message = ValidationMessages.IS_REQUIRED)
    @Size(min = 3, max = 50, message = ValidationMessages.BETWEEN_2_AND_50_LETTERS)
    private String title;

    @NotBlank(message = ValidationMessages.IS_REQUIRED)
    @Size(min = 3, max = 500, message = ValidationMessages.BETWEEN_10_AND_1000_LETTERS)
    private String instruction;

    public CreateRecipeInstructionForm() {
    }

    public CreateRecipeInstructionForm(Integer id, String title, String instruction) {
        this.id = id;
        this.title = title;
        this.instruction = instruction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
