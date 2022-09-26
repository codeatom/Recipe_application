package recipe_application.application.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_application.application.data.repo.RecipeIngredientRepository;
import recipe_application.application.data.service.RecipeIngredientService;
import recipe_application.application.model.RecipeIngredient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        if(recipeIngredient == null ){
            throw new IllegalArgumentException ("recipeIngredient is null");
        }

        return recipeIngredientRepository.save(recipeIngredient);
    }

    @Override
    public List<RecipeIngredient> saveAll(List<RecipeIngredient> recipeIngredientList) {
        if(recipeIngredientList == null ){
            throw new IllegalArgumentException ("recipeIngredientList is null");
        }

        return (List<RecipeIngredient>) recipeIngredientRepository.saveAll(recipeIngredientList);
    }

    @Override
    public Optional<RecipeIngredient> findById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        return recipeIngredientRepository.findById(id).isPresent() ?
                Optional.of(recipeIngredientRepository.findById(id).get()) :
                Optional.empty();
    }

    @Override
    public Collection<RecipeIngredient> findAll() {
        return (Collection<RecipeIngredient>) recipeIngredientRepository.findAll();
    }

    public List<RecipeIngredient> findAllByIngredientId(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        return recipeIngredientRepository.findAllByIngredientId(id);
    }

    @Override
    public RecipeIngredient update(RecipeIngredient recipeIngredient) {
        if(recipeIngredient == null ){
            throw new IllegalArgumentException ("recipeIngredient is null");
        }

        return save(recipeIngredient);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(recipeIngredientRepository.existsById(id)){
            removeAssociatedEntity(id);
            recipeIngredientRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(RecipeIngredient recipeIngredient) {
        if(recipeIngredientRepository.existsById(recipeIngredient.getId())){
            removeAssociatedEntity(recipeIngredient.getId());
            recipeIngredientRepository.delete(recipeIngredient);
            return true;
        }

        return false;
    }

    private void removeAssociatedEntity(Integer id){
        RecipeIngredient recipeIngredient = findById(id).get();

        recipeIngredient.setIngredient(null);
        recipeIngredient.setRecipe(null);
    }

}
