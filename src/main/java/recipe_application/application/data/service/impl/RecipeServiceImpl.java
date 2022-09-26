package recipe_application.application.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_application.application.data.repo.RecipeCategoryRepository;
import recipe_application.application.data.repo.RecipeIngredientRepository;
import recipe_application.application.data.repo.RecipeRepository;
import recipe_application.application.data.service.RecipeService;
import recipe_application.application.model.Recipe;
import recipe_application.application.model.RecipeCategory;
import recipe_application.application.model.RecipeIngredient;

import java.util.*;


@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCategoryRepository recipeCategoryRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCategoryRepository recipeCategoryRepository,
                             RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public Recipe save(Recipe recipe) {
        if(recipe == null ){
            throw new IllegalArgumentException ("recipe is null");
        }

        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> saveAll(List<Recipe> recipeList){
        if(recipeList == null ){
            throw new IllegalArgumentException ("recipeList is null");
        }

        return (List<Recipe>) recipeRepository.saveAll(recipeList);
    }

    @Override
    public Optional<Recipe> findById(Integer id) {
        return recipeRepository.findById(id).isPresent() ?
                Optional.of(recipeRepository.findById(id).get()) :
                Optional.empty();
    }

    @Override
    public Collection<Recipe> findAll() {
        return (Collection<Recipe>) recipeRepository.findAll();
    }

    public List<Recipe> findAllByInstructionId(Integer id){
        if(id < 1){
            throw new IllegalArgumentException ("id is 0");
        }

        return recipeRepository.findAllByInstructionId(id);
    }

    @Override
    public List<Recipe> findAllByCategoriesCategory(String category) {
        if(category == null ){
            throw new IllegalArgumentException ("category is null");
        }

        return recipeRepository.findAllByCategoriesCategory(category);
    }

    @Override
    public List<Recipe> findAllByRecipeNameContainingIgnoreCase(String recipeName){
        if(recipeName == null ){
            throw new IllegalArgumentException ("recipeName is null");
        }

        return  recipeRepository.findAllByRecipeNameContainingIgnoreCase(recipeName);
    }

    @Override
    public List<Recipe> findDistinctByCategoriesCategoryIn(Collection<String> categoryList) {
        if(categoryList == null ){
            throw new IllegalArgumentException ("categoryList is null");
        }

        return recipeRepository.findDistinctByCategoriesCategoryIn(categoryList);
    }

    @Override
    public List<Recipe> findAllByRecipeIngredientsIngredientIngredientName(String ingredientName) {
        if(ingredientName == null ){
            throw new IllegalArgumentException ("ingredientName is null");
        }

        return recipeRepository.findAllByRecipeIngredientsIngredientIngredientName(ingredientName);
    }

    @Override
    public Recipe update(Recipe recipe) {
        if(recipe == null ){
            throw new IllegalArgumentException ("recipe is null");
        }

        return save(recipe);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(recipeRepository.existsById(id)){
            removeAssociatedEntity(id);
            recipeRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Recipe recipe) {
        if(recipeRepository.existsById(recipe.getId())){
            removeAssociatedEntity(recipe.getId());
            recipeRepository.delete(recipe);
            return true;
        }

        return false;
    }

    private void removeAssociatedEntity(Integer id){
        Recipe recipe = findById(id).get();
        List<RecipeCategory> recipeCategoryList = (List<RecipeCategory>) recipeCategoryRepository.findAll();
        List<RecipeIngredient> recipeIngredientList = (List<RecipeIngredient>) recipeIngredientRepository.findAll();

        recipeCategoryList.forEach(recipeCategory ->  recipeCategory.getRecipes().remove(recipe));

        recipeIngredientList.forEach(recipeIngredient -> recipeIngredient.setRecipe(null));

        recipe.setInstruction(null);
    }

}
