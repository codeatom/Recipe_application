package recipe_application.application.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "RECIPECATEGORY")
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category")
    private String category;

    @ManyToMany(mappedBy = "categories", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Recipe> recipes = new HashSet<>();

    public RecipeCategory() {
    }

    public RecipeCategory(String category) {
        this.category = category;
    }

    public RecipeCategory(String category, Set<Recipe> recipes) {
        this.category = category;
        this.recipes = recipes;
    }

    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        if (recipe == null) throw new IllegalArgumentException("recipe is null");

        recipes.add(recipe);
        recipe.getCategories().add(this);
    }

    public void removeRecipe(Recipe recipe) {
        if (recipe == null) throw new IllegalArgumentException("recipe is null");

        recipes.remove(recipe);
        recipe.getCategories().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategory that = (RecipeCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }

    @Override
    public String toString() {
        return "RecipeCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
