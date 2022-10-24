package recipe_application.application.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_application.application.data.converter.Converter;
import recipe_application.application.data.repo.RecipeCategoryRepository;
import recipe_application.application.data.repo.RecipeIngredientRepository;
import recipe_application.application.data.repo.RecipeInstructionRepository;
import recipe_application.application.data.repo.RecipeRepository;
import recipe_application.application.data.service.RecipeService;
import recipe_application.application.dto.forms.recipeForm.CreateRecipeForm;
import recipe_application.application.dto.forms.recipeForm.UpdateRecipeForm;
import recipe_application.application.dto.views.RecipeView;
import recipe_application.application.model.Recipe;
import recipe_application.application.model.RecipeCategory;
import recipe_application.application.model.RecipeIngredient;

import java.util.*;


@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCategoryRepository recipeCategoryRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeInstructionRepository recipeInstructionRepository;
    private final Converter converter;


    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCategoryRepository recipeCategoryRepository,
                             RecipeIngredientRepository recipeIngredientRepository,
                             RecipeInstructionRepository recipeInstructionRepository,
                             Converter converter)
    {
        this.recipeRepository = recipeRepository;
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeInstructionRepository = recipeInstructionRepository;
        this.converter = converter;
    }

    @Override
    public RecipeView save(CreateRecipeForm createRecipeForm) {
        if(createRecipeForm == null ){
            throw new IllegalArgumentException ("createRecipeForm is null");
        }

        Recipe recipe = new Recipe(createRecipeForm.getRecipeName());
        recipe.setInstruction(recipeInstructionRepository.findById(createRecipeForm.getInstructionId()).get());
        Recipe savedRecipe = recipeRepository.save(recipe);

        return converter.recipeToView(savedRecipe);
    }

    @Override
    public RecipeView findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        return recipeRepository.findById(id).isPresent() ?
                converter.recipeToView(recipeRepository.findById(id).get()) :
                null;
    }

    @Override
    public Collection<RecipeView> findAll() {
        Collection<Recipe> recipeList = (Collection<Recipe>) recipeRepository.findAll();
        return converter.recipeListToViewList(recipeList);
    }

    public List<RecipeView> findAllByInstructionId(Integer id){
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        List<Recipe> recipeList = recipeRepository.findAllByInstructionId(id);
        return new ArrayList<>(converter.recipeListToViewList(recipeList));
    }

    @Override
    public List<RecipeView> findAllByCategoriesCategory(String category) {
        if(category == null ){
            throw new IllegalArgumentException ("category is null");
        }

        List<Recipe> recipeList = recipeRepository.findAllByCategoriesCategory(category);
        return new ArrayList<>(converter.recipeListToViewList(recipeList));
    }

    @Override
    public List<RecipeView> findAllByRecipeNameContainingIgnoreCase(String recipeName){
        if(recipeName == null ){
            throw new IllegalArgumentException ("recipeName is null");
        }

        List<Recipe> recipeList = recipeRepository.findAllByRecipeNameContainingIgnoreCase(recipeName);
        return new ArrayList<>(converter.recipeListToViewList(recipeList));
    }

    @Override
    public List<RecipeView> findDistinctByCategoriesCategoryIn(Collection<String> categoryList) {
        if(categoryList == null ){
            throw new IllegalArgumentException ("categoryList is null");
        }

        List<Recipe> recipeList = recipeRepository.findDistinctByCategoriesCategoryIn(categoryList);
        return new ArrayList<>(converter.recipeListToViewList(recipeList));
    }

    @Override
    public List<RecipeView> findAllByRecipeIngredientsIngredientIngredientName(String ingredientName) {
        if(ingredientName == null ){
            throw new IllegalArgumentException ("ingredientName is null");
        }

        List<Recipe> recipeList = recipeRepository.findAllByRecipeIngredientsIngredientIngredientName(ingredientName);
        return new ArrayList<>(converter.recipeListToViewList(recipeList));
    }

    @Override
    public RecipeView update(UpdateRecipeForm updateRecipeForm) {
        if(updateRecipeForm == null ){
            throw new IllegalArgumentException ("updateRecipeForm is null");
        }

        Recipe recipe= recipeRepository.findById(updateRecipeForm.getId()).isPresent() ?
                recipeRepository.findById(updateRecipeForm.getId()).get() :
                null;

        if(recipe == null){
            return null;
        }

        recipe.setRecipeName(updateRecipeForm.getRecipeName());
        recipe.setInstruction(recipeInstructionRepository.findById(updateRecipeForm.getInstructionId()).get());
        recipeRepository.save(recipe);

        return converter.recipeToView(recipe);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(recipeRepository.existsById(id)){
            removeAssociatedEntity(id);
            recipeRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Recipe recipe) {
        if(recipe == null ){
            throw new IllegalArgumentException ("recipe is null");
        }

        if(recipeRepository.existsById(recipe.getId())){
            removeAssociatedEntity(recipe.getId());
            recipeRepository.delete(recipe);
            return true;
        }

        return false;
    }

    private void removeAssociatedEntity(Integer id){
        Recipe recipe = recipeRepository.findById(id).get();
        List<RecipeCategory> recipeCategoryList = (List<RecipeCategory>) recipeCategoryRepository.findAll();
        List<RecipeIngredient> recipeIngredientList = (List<RecipeIngredient>) recipeIngredientRepository.findAll();

        recipeCategoryList.forEach(recipeCategory ->  recipeCategory.getRecipes().remove(recipe));

        recipeIngredientList.forEach(recipeIngredient -> recipeIngredient.setRecipe(null));

        // recipe.setInstruction(null);
    }

}










//package recipe_application.application.data.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import recipe_application.application.data.repo.RecipeCategoryRepository;
//import recipe_application.application.data.repo.RecipeIngredientRepository;
//import recipe_application.application.data.repo.RecipeRepository;
//import recipe_application.application.data.service.RecipeService;
//import recipe_application.application.model.Recipe;
//import recipe_application.application.model.RecipeCategory;
//import recipe_application.application.model.RecipeIngredient;
//
//import java.util.*;
//
//
//@Service
//public class RecipeServiceImpl implements RecipeService {
//
//    private final RecipeRepository recipeRepository;
//    private final RecipeCategoryRepository recipeCategoryRepository;
//    private final RecipeIngredientRepository recipeIngredientRepository;
//
//    @Autowired
//    public RecipeServiceImpl(RecipeRepository recipeRepository,
//                             RecipeCategoryRepository recipeCategoryRepository,
//                             RecipeIngredientRepository recipeIngredientRepository) {
//        this.recipeRepository = recipeRepository;
//        this.recipeCategoryRepository = recipeCategoryRepository;
//        this.recipeIngredientRepository = recipeIngredientRepository;
//    }
//
//    @Override
//    public Recipe save(Recipe recipe) {
//        if(recipe == null ){
//            throw new IllegalArgumentException ("recipe is null");
//        }
//
//        return recipeRepository.save(recipe);
//    }
//
//    @Override
//    public List<Recipe> saveAll(List<Recipe> recipeList){
//        if(recipeList == null ){
//            throw new IllegalArgumentException ("recipeList is null");
//        }
//
//        return (List<Recipe>) recipeRepository.saveAll(recipeList);
//    }
//
//    @Override
//    public Optional<Recipe> findById(Integer id) {
//        return recipeRepository.findById(id).isPresent() ?
//                Optional.of(recipeRepository.findById(id).get()) :
//                Optional.empty();
//    }
//
//    @Override
//    public Collection<Recipe> findAll() {
//        return (Collection<Recipe>) recipeRepository.findAll();
//    }
//
//    public List<Recipe> findAllByInstructionId(Integer id){
//        if(id < 1){
//            throw new IllegalArgumentException ("id is 0");
//        }
//
//        return recipeRepository.findAllByInstructionId(id);
//    }
//
//    @Override
//    public List<Recipe> findAllByCategoriesCategory(String category) {
//        if(category == null ){
//            throw new IllegalArgumentException ("category is null");
//        }
//
//        return recipeRepository.findAllByCategoriesCategory(category);
//    }
//
//    @Override
//    public List<Recipe> findAllByRecipeNameContainingIgnoreCase(String recipeName){
//        if(recipeName == null ){
//            throw new IllegalArgumentException ("recipeName is null");
//        }
//
//        return  recipeRepository.findAllByRecipeNameContainingIgnoreCase(recipeName);
//    }
//
//    @Override
//    public List<Recipe> findDistinctByCategoriesCategoryIn(Collection<String> categoryList) {
//        if(categoryList == null ){
//            throw new IllegalArgumentException ("categoryList is null");
//        }
//
//        return recipeRepository.findDistinctByCategoriesCategoryIn(categoryList);
//    }
//
//    @Override
//    public List<Recipe> findAllByRecipeIngredientsIngredientIngredientName(String ingredientName) {
//        if(ingredientName == null ){
//            throw new IllegalArgumentException ("ingredientName is null");
//        }
//
//        return recipeRepository.findAllByRecipeIngredientsIngredientIngredientName(ingredientName);
//    }
//
//    @Override
//    public Recipe update(Recipe recipe) {
//        if(recipe == null ){
//            throw new IllegalArgumentException ("recipe is null");
//        }
//
//        return save(recipe);
//    }
//
//    @Override
//    public boolean deleteById(Integer id) {
//        if(recipeRepository.existsById(id)){
//            removeAssociatedEntity(id);
//            recipeRepository.deleteById(id);
//            return true;
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean delete(Recipe recipe) {
//        if(recipeRepository.existsById(recipe.getId())){
//            removeAssociatedEntity(recipe.getId());
//            recipeRepository.delete(recipe);
//            return true;
//        }
//
//        return false;
//    }
//
//    private void removeAssociatedEntity(Integer id){
//        Recipe recipe = findById(id).get();
//        List<RecipeCategory> recipeCategoryList = (List<RecipeCategory>) recipeCategoryRepository.findAll();
//        List<RecipeIngredient> recipeIngredientList = (List<RecipeIngredient>) recipeIngredientRepository.findAll();
//
//        recipeCategoryList.forEach(recipeCategory ->  recipeCategory.getRecipes().remove(recipe));
//
//        recipeIngredientList.forEach(recipeIngredient -> recipeIngredient.setRecipe(null));
//
//        recipe.setInstruction(null);
//    }
//
//}
