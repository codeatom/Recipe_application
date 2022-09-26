package recipe_application.application.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_application.application.data.repo.RecipeCategoryRepository;
import recipe_application.application.data.repo.RecipeRepository;
import recipe_application.application.data.service.RecipeCategoryService;
import recipe_application.application.model.Recipe;
import recipe_application.application.model.RecipeCategory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class RecipeCategoryServiceImpl implements RecipeCategoryService {

    private final RecipeCategoryRepository recipeCategoryRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeCategoryServiceImpl(RecipeCategoryRepository recipeCategoryRepository, RecipeRepository recipeRepository) {
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeCategory save(RecipeCategory recipeCategory) {
        if(recipeCategory == null ){
            throw new IllegalArgumentException ("recipeCategory is null");
        }

        return recipeCategoryRepository.save(recipeCategory);
    }

    @Override
    public List<RecipeCategory> saveAll(List<RecipeCategory> recipeCategoryList){
        if(recipeCategoryList == null ){
            throw new IllegalArgumentException ("recipeCategoryList is null");
        }

        return (List<RecipeCategory>) recipeCategoryRepository.saveAll(recipeCategoryList);
    }

    @Override
    public Optional<RecipeCategory> findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        return recipeCategoryRepository.findById(id).isPresent() ?
                Optional.of(recipeCategoryRepository.findById(id).get()) :
                Optional.empty();
    }

    @Override
    public Collection<RecipeCategory> findAll() {
        return (Collection<RecipeCategory>) recipeCategoryRepository.findAll();
    }

    @Override
    public RecipeCategory update(RecipeCategory recipeCategory) {
        if(recipeCategory == null ){
            throw new IllegalArgumentException ("recipeCategory is null");
        }

        return save(recipeCategory);
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
        RecipeCategory recipeCategory = findById(id).get();
        List<Recipe> recipeList = (List<Recipe>) recipeRepository.findAll();

        recipeList.forEach(recipe -> recipe.getCategories().remove(recipeCategory));
    }

}
