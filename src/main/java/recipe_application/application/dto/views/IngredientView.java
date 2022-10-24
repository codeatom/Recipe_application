package recipe_application.application.dto.views;

import java.util.Objects;


public class IngredientView {

    private final Integer id;
    private final String ingredientName;

    public IngredientView(Integer id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
    }

    public Integer getId() {
        return id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientView that = (IngredientView) o;
        return Objects.equals(id, that.id) && Objects.equals(ingredientName, that.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredientName);
    }
}
