package recipe_application.application.model;

import recipe_application.application.model.measurement.Measurement;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RECIPEINGREDIENT")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "measurement")
    @Enumerated(EnumType.STRING)
    private Measurement measurement;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(double amount, Measurement measurement) {
        this.amount = amount;
        this.measurement = measurement;
    }

    public RecipeIngredient(double amount, Measurement measurement, Ingredient ingredient) {
        this.amount = amount;
        this.measurement = measurement;
        this.ingredient = ingredient;
    }

    public RecipeIngredient(double amount, Measurement measurement, Recipe recipe) {
        this.amount = amount;
        this.measurement = measurement;
        this.recipe = recipe;
    }

    public RecipeIngredient(double amount, Measurement measurement, Ingredient ingredient, Recipe recipe) {
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && measurement == that.measurement && Objects.equals(ingredient, that.ingredient) && Objects.equals(recipe, that.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, measurement, ingredient, recipe);
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "id=" + id +
                ", amount=" + amount +
                ", measurement=" + measurement +
                '}';
    }
}
