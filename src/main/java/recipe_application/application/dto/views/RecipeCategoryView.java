package recipe_application.application.dto.views;

import recipe_application.application.model.Recipe;

import java.util.Objects;
import java.util.Set;


public class RecipeCategoryView {

    private final Integer id;
    private final String category;
    private final Set<Recipe> recipes;

    public RecipeCategoryView(Integer id, String category, Set<Recipe> recipes) {
        this.id = id;
        this.category = category;
        this.recipes = recipes;
    }

    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategoryView that = (RecipeCategoryView) o;
        return Objects.equals(id, that.id) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }
}
