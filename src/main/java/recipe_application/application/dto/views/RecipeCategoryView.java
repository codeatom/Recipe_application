package recipe_application.application.dto.views;

import java.util.Set;


public class RecipeCategoryView {

    private final Integer id;
    private final String category;
    private Set<RecipeView> recipeViews;

    public RecipeCategoryView(Integer id, String category) {
        this.id = id;
        this.category = category;
    }

    public RecipeCategoryView(Integer id, String category, Set<RecipeView> recipeViews) {
        this(id, category);
        this.recipeViews = recipeViews;
    }

    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public Set<RecipeView> getRecipeViews() {
        return recipeViews;
    }
}
