package recipe_application.application.dto.forms.recipeIngredientForm;

import org.springframework.stereotype.Component;
import recipe_application.application.model.Ingredient;
import recipe_application.application.model.measurement.Measurement;
import recipe_application.application.util.ValidationMessages;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;


@Component
public class CreateRecipeIngredientForm {

    @Null
    private Integer id;

    @Positive
    @NotNull(message = ValidationMessages.IS_REQUIRED)
    private Double amount;

    @NotNull
    private Measurement measurement;

    @NotNull(message = ValidationMessages.IS_REQUIRED)
    private Integer ingredientId;

    private Ingredient ingredient;

    public CreateRecipeIngredientForm() {
    }

    public CreateRecipeIngredientForm(Integer id, Double amount, Measurement measurement) {
        this.id = id;
        this.amount = amount;
        this.measurement = measurement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
