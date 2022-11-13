package recipe_application.application.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipe_application.application.data.converter.Converter;
import recipe_application.application.data.repo.IngredientRepository;
import recipe_application.application.data.repo.RecipeIngredientRepository;
import recipe_application.application.data.repo.RecipeRepository;
import recipe_application.application.data.service.RecipeIngredientService;
import recipe_application.application.dto.forms.recipeIngredientForm.AddRecipeForm;
import recipe_application.application.dto.forms.recipeIngredientForm.CreateRecipeIngredientForm;
import recipe_application.application.dto.forms.recipeIngredientForm.UpdateRecipeIngredientForm;
import recipe_application.application.dto.views.RecipeIngredientView;
import recipe_application.application.exception.ResourceNotFoundException;
import recipe_application.application.model.Ingredient;
import recipe_application.application.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Transactional
@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final Converter converter;

    @Autowired
    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository, Converter converter) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.converter = converter;
    }

    @Override
    public RecipeIngredientView save(CreateRecipeIngredientForm recipeIngredientForm) {
        if(recipeIngredientForm == null ){
            throw new IllegalArgumentException ("recipeIngredientForm is null");
        }

        Ingredient ingredient = ingredientRepository
                .findById(recipeIngredientForm.getIngredientId())
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient with id " + recipeIngredientForm.getIngredientId() + " not found."));

        RecipeIngredient recipeIngredient = new RecipeIngredient(
                recipeIngredientForm.getAmount(),
                recipeIngredientForm.getMeasurement(),
                ingredient);

        RecipeIngredient savedRecipeIngredient = recipeIngredientRepository.save(recipeIngredient);

        return converter.recipeIngredientToView(savedRecipeIngredient);
    }

    @Override
    public RecipeIngredientView findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(recipeIngredientRepository.findById(id).isPresent()){
            return converter.recipeIngredientToView(recipeIngredientRepository.findById(id).get());
        }

        throw new ResourceNotFoundException("Recipe ingredient with id " + id + " not found.");
    }

    @Override
    public Collection<RecipeIngredientView> findAll() {
        Collection<RecipeIngredient> recipeIngredientList = (Collection<RecipeIngredient>) recipeIngredientRepository.findAll();
        return converter.recipeIngredientListToViewList(recipeIngredientList);
    }

    @Override
    public List<RecipeIngredientView> findAllByIngredientId(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findAllByIngredientId(id);
        return new ArrayList<>(converter.recipeIngredientListToViewList(recipeIngredientList));
    }

    @Override
    public Collection<RecipeIngredientView> findAllNotAssociatedWithRecipe() {
        Collection<RecipeIngredient> recipeIngredientList = (Collection<RecipeIngredient>) recipeIngredientRepository.findAll();
        recipeIngredientList.removeIf(recipeIngredient -> recipeIngredient.getRecipe() != null);

        return converter.recipeIngredientListToViewList(recipeIngredientList);
    }

    @Override
    public RecipeIngredientView update(UpdateRecipeIngredientForm updateRecipeIngredientForm) {
        if(updateRecipeIngredientForm == null ){
            throw new IllegalArgumentException ("updateRecipeIngredientForm is null");
        }

        RecipeIngredient recipeIngredient = recipeIngredientRepository
                .findById(updateRecipeIngredientForm.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe Ingredient with id " + updateRecipeIngredientForm.getId() + " not found."));

        Ingredient ingredient = ingredientRepository
                .findById(updateRecipeIngredientForm.getIngredientId())
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient with id " + updateRecipeIngredientForm.getIngredientId() + " not found."));

        recipeIngredient.setAmount(updateRecipeIngredientForm.getAmount());
        recipeIngredient.setMeasurement(updateRecipeIngredientForm.getMeasurement());
        recipeIngredient.setIngredient(ingredient);

        return converter.recipeIngredientToView(recipeIngredient);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(recipeIngredientRepository.existsById(id)){
            removeAssociatedEntity(id);
            recipeIngredientRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(RecipeIngredient recipeIngredient) {
        if(recipeIngredient == null ){
            throw new IllegalArgumentException ("recipeIngredient is null");
        }

        if(recipeIngredientRepository.existsById(recipeIngredient.getId())){
            removeAssociatedEntity(recipeIngredient.getId());
            recipeIngredientRepository.delete(recipeIngredient);
            return true;
        }

        return false;
    }

    @Override
    public RecipeIngredientView addRecipe(AddRecipeForm addRecipeForm){
        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(addRecipeForm.getId()).isPresent() ?
                recipeIngredientRepository.findById(addRecipeForm.getId()).get() :
                null;

        if(recipeIngredient == null){
            return null;
        }

        if(recipeRepository.findById(addRecipeForm.getRecipeId()).isPresent()){
            recipeIngredient.addRecipe(recipeRepository.findById(addRecipeForm.getRecipeId()).get());
        }

        return converter.recipeIngredientToView(recipeIngredient);
    }

    public void removeRecipe(Integer id){
        if(recipeIngredientRepository.findById(id).isPresent()){
            RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(id).get();

            recipeIngredient.removeRecipe(recipeIngredient.getRecipe());
        }
    }

    private void removeAssociatedEntity(Integer id){
        RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(id).get();

        recipeIngredient.setIngredient(null);
        recipeIngredient.setRecipe(null);
    }

}
