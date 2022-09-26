package recipe_application.application.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_application.application.data.repo.IngredientRepository;
import recipe_application.application.data.repo.RecipeIngredientRepository;
import recipe_application.application.data.service.IngredientService;
import recipe_application.application.model.Ingredient;
import recipe_application.application.model.RecipeIngredient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        if(ingredient == null ){
            throw new IllegalArgumentException ("ingredient is null");
        }

        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> saveAll(List<Ingredient> ingredientList){
        if(ingredientList == null ){
            throw new IllegalArgumentException ("ingredientList is null");
        }

        return (List<Ingredient>) ingredientRepository.saveAll(ingredientList);
    }

    @Override
    public Optional<Ingredient> findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        return ingredientRepository.findById(id).isPresent() ?
                Optional.of(ingredientRepository.findById(id).get()) :
                Optional.empty();
    }

    @Override
    public Collection<Ingredient> findAll() {
        return (Collection<Ingredient>) ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findByIngredientNameIgnoreCase(String ingredientName) {
        if(ingredientName == null ){
            throw new IllegalArgumentException ("ingredientName is null");
        }

        return ingredientRepository.findByIngredientNameIgnoreCase(ingredientName);
    }

    @Override
    public List<Ingredient> findByIngredientNameContainsIgnoreCase(String ingredientName) {
        if(ingredientName == null ){
            throw new IllegalArgumentException ("ingredientName is null");
        }

        return ingredientRepository.findByIngredientNameContainsIgnoreCase(ingredientName);
    }

    @Override
    public Ingredient update(Ingredient ingredient) {
        if(ingredient == null ){
            throw new IllegalArgumentException ("ingredient is null");
        }

        return  save(ingredient);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(ingredientRepository.existsById(id)){
            removeAssociatedEntity(id);
            ingredientRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Ingredient ingredient) {
        if(ingredient == null ){
            throw new IllegalArgumentException ("ingredient is null");
        }

        if(ingredientRepository.existsById(ingredient.getId())){
            removeAssociatedEntity(ingredient.getId());
            ingredientRepository.delete(ingredient);
            return true;
        }

        return false;
    }

    private void removeAssociatedEntity(Integer id){
        List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findAllByIngredientId(id);

        recipeIngredientList.forEach(recipeIngredient -> recipeIngredient.setIngredient(null));
    }

}
