package recipe_application.application.dto.views;

import recipe_application.application.model.Ingredient;
import recipe_application.application.model.Recipe;
import recipe_application.application.model.measurement.Measurement;

import java.util.Objects;


public class RecipeIngredientView {

    private final Integer id;
    private final double amount;
    private final Measurement measurement;
    private final Ingredient ingredient;
    private final Recipe recipe;

    public RecipeIngredientView(Integer id, double amount, Measurement measurement, Ingredient ingredient, Recipe recipe) {
        this.id = id;
        this.amount = amount;
        this.measurement = measurement;
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    public Integer getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientView that = (RecipeIngredientView) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && measurement == that.measurement && Objects.equals(ingredient, that.ingredient) && Objects.equals(recipe, that.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, measurement, ingredient, recipe);
    }
}
