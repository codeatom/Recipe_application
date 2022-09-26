package recipe_application.application.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "RECIPE")
public class Recipe {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "recipename")
    private String recipeName;

    @OneToOne(cascade = CascadeType.ALL)
    private RecipeInstruction instruction;

    @OneToMany(mappedBy = "recipe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "recipe_recipecategory", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "recipecategory_id"))
    private Set<RecipeCategory> categories = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String recipeName, RecipeInstruction instruction) {
        this.recipeName = recipeName;
        this.instruction = instruction;
    }

    public Recipe(String recipeName, RecipeInstruction instruction, List<RecipeIngredient> recipeIngredients) {
        this.recipeName = recipeName;
        this.instruction = instruction;
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe(String recipeName, RecipeInstruction instruction, Set<RecipeCategory> categories) {
        this.recipeName = recipeName;
        this.instruction = instruction;
        this.categories = categories;
    }

    public Recipe(String recipeName, RecipeInstruction instruction, List<RecipeIngredient> recipeIngredients, Set<RecipeCategory> categories) {
        this.recipeName = recipeName;
        this.instruction = instruction;
        this.recipeIngredients = recipeIngredients;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public RecipeInstruction getInstruction() {
        return instruction;
    }

    public void setInstruction(RecipeInstruction instruction) {
        this.instruction = instruction;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public Set<RecipeCategory> getCategories() {
        return categories;
    }

    public void addCategory(RecipeCategory recipeCategory) {
        if (recipeCategory == null) throw new IllegalArgumentException("recipeCategory is null");

        categories.add(recipeCategory);
        recipeCategory.getRecipes().add(this);
    }

    public void removeRecipeCategory(RecipeCategory recipeCategory) {
        if (recipeCategory == null) throw new IllegalArgumentException("recipeCategory is null");

        categories.remove(recipeCategory);
        recipeCategory.getRecipes().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(recipeName, recipe.recipeName) && Objects.equals(instruction, recipe.instruction) && Objects.equals(recipeIngredients, recipe.recipeIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeName, instruction, recipeIngredients);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                '}';
    }
}
