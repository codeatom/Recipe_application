package recipe_application.application.dto.views;

import recipe_application.application.model.measurement.Measurement;


public class RecipeIngredientView {

    private final Integer id;
    private final double amount;
    private final Measurement measurement;
    private final IngredientView ingredientView;
    private RecipeView recipeView;

    public RecipeIngredientView(Integer id, double amount, Measurement measurement, IngredientView ingredientView) {
        this.id = id;
        this.amount = amount;
        this.measurement = measurement;
        this.ingredientView = ingredientView;
    }

    public RecipeIngredientView(Integer id, double amount, Measurement measurement, IngredientView ingredientView, RecipeView recipeView) {
        this(id, amount, measurement, ingredientView);
        this.recipeView = recipeView;
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

    public IngredientView getIngredientView() {
        return ingredientView;
    }

    public RecipeView getRecipeView() {
        return recipeView;
    }
}
