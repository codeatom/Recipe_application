package recipe_application.application.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_application.application.data.converter.Converter;
import recipe_application.application.data.repo.RecipeCategoryRepository;
import recipe_application.application.data.repo.RecipeRepository;
import recipe_application.application.data.service.RecipeCategoryService;
import recipe_application.application.dto.forms.recipeCategoryForm.CreateRecipeCategoryForm;
import recipe_application.application.dto.forms.recipeCategoryForm.UpdateRecipeCategoryForm;
import recipe_application.application.dto.views.RecipeCategoryView;
import recipe_application.application.exception.ResourceNotFoundException;
import recipe_application.application.model.Recipe;
import recipe_application.application.model.RecipeCategory;

import java.util.Collection;
import java.util.List;


@Service
public class RecipeCategoryServiceImpl implements RecipeCategoryService {

    private final RecipeCategoryRepository recipeCategoryRepository;
    private final RecipeRepository recipeRepository;
    private final Converter converter;

    @Autowired
    public RecipeCategoryServiceImpl(RecipeCategoryRepository recipeCategoryRepository, RecipeRepository recipeRepository, Converter converter) {
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.recipeRepository = recipeRepository;
        this.converter = converter;
    }

    @Override
    public RecipeCategoryView save(CreateRecipeCategoryForm createRecipeCategoryForm) {
        if(createRecipeCategoryForm == null ){
            throw new IllegalArgumentException ("createRecipeCategoryForm is null");
        }

        RecipeCategory recipeCategory = recipeCategoryRepository.save(new RecipeCategory(createRecipeCategoryForm.getCategory()));
        return converter.recipeCategoryToView(recipeCategory);
    }

    @Override
    public RecipeCategoryView findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(recipeCategoryRepository.findById(id).isPresent()){
            converter.recipeCategoryToView(recipeCategoryRepository.findById(id).get());
        }

        throw new ResourceNotFoundException("Recipe category with id " + id + " not found.");
    }

    @Override
    public Collection<RecipeCategoryView> findAll() {
        Collection<RecipeCategory> recipeCategoryList = (Collection<RecipeCategory>) recipeCategoryRepository.findAll();
        return converter.recipeCategoryListToViewList(recipeCategoryList);
    }

    @Override
    public RecipeCategoryView update(UpdateRecipeCategoryForm updateRecipeCategoryForm) {
        if(updateRecipeCategoryForm == null ){
            throw new IllegalArgumentException ("updateRecipeCategoryForm is null");
        }

        RecipeCategory recipeCategory = recipeCategoryRepository.findById(updateRecipeCategoryForm.getId()).isPresent() ?
                recipeCategoryRepository.findById(updateRecipeCategoryForm.getId()).get() :
                null;

        if(recipeCategory == null){
            throw new ResourceNotFoundException("Recipe Category with id " + updateRecipeCategoryForm.getId() + " not found.");
        }

        recipeCategory.setCategory(updateRecipeCategoryForm.getCategory());
        recipeCategoryRepository.save(recipeCategory);

        return converter.recipeCategoryToView(recipeCategory);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(recipeCategoryRepository.existsById(id)){
            removeAssociatedEntity(id);
            recipeCategoryRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(RecipeCategory recipeCategory) {
        if(recipeCategory == null ){
            throw new IllegalArgumentException ("recipeCategory is null");
        }

        if(recipeCategoryRepository.existsById(recipeCategory.getId())){
            removeAssociatedEntity(recipeCategory.getId());
            recipeCategoryRepository.delete(recipeCategory);
            return true;
        }

        return false;
    }

    private void removeAssociatedEntity(Integer id){
        RecipeCategory recipeCategory = recipeCategoryRepository.findById(id).get();
        List<Recipe> recipeList = (List<Recipe>) recipeRepository.findAll();

        recipeList.forEach(recipe -> recipe.getCategories().remove(recipeCategory));
    }

}
