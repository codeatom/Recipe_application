package recipe_application.application.dto.forms.recipeForm;

import org.springframework.stereotype.Component;
import recipe_application.application.util.ValidationMessages;

import javax.validation.constraints.*;


@Component
public class UpdateRecipeForm {

    @Positive
    @NotNull
    private Integer id;

    @NotBlank(message = ValidationMessages.IS_REQUIRED)
    @Size(min = 2, max = 50, message = ValidationMessages.BETWEEN_2_AND_50_LETTERS)
    private String recipeName;

    @NotNull
    @Positive
    private Integer instructionId;

    @NotBlank(message = ValidationMessages.IS_REQUIRED)
    @Size(min = 3, max = 50, message = ValidationMessages.BETWEEN_2_AND_50_LETTERS)
    private String instructionTitle;

    @NotBlank(message = ValidationMessages.IS_REQUIRED)
    @Size(min = 3, max = 500, message = ValidationMessages.BETWEEN_10_AND_1000_LETTERS)
    private String instructionDetail;

    public UpdateRecipeForm() {
    }

    public UpdateRecipeForm(Integer id, String recipeName) {
        this.id = id;
        this.recipeName = recipeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Integer getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(Integer instructionId) {
        this.instructionId = instructionId;
    }

    public String getInstructionTitle() {
        return instructionTitle;
    }

    public void setInstructionTitle(String instructionTitle) {
        this.instructionTitle = instructionTitle;
    }

    public String getInstructionDetail() {
        return instructionDetail;
    }

    public void setInstructionDetail(String instructionDetail) {
        this.instructionDetail = instructionDetail;
    }
}
