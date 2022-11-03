package recipe_application.application.dto.views;


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

}
