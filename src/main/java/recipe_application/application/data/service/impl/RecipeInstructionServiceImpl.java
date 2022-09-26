package recipe_application.application.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_application.application.data.repo.RecipeInstructionRepository;
import recipe_application.application.data.repo.RecipeRepository;
import recipe_application.application.data.service.RecipeInstructionService;
import recipe_application.application.model.Recipe;
import recipe_application.application.model.RecipeInstruction;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class RecipeInstructionServiceImpl implements RecipeInstructionService {

    private final RecipeInstructionRepository recipeInstructionRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeInstructionServiceImpl(RecipeInstructionRepository recipeInstructionRepository, RecipeRepository recipeRepository) {
        this.recipeInstructionRepository = recipeInstructionRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeInstruction save(RecipeInstruction recipeInstruction) {
        if(recipeInstruction == null ){
            throw new IllegalArgumentException ("recipeInstruction is null");
        }

        return recipeInstructionRepository.save(recipeInstruction);
    }

    @Override
    public List<RecipeInstruction> saveAll(List<RecipeInstruction> recipeInstructionList){
        if(recipeInstructionList == null ){
            throw new IllegalArgumentException ("recipeInstructionList is null");
        }

        return (List<RecipeInstruction>) recipeInstructionRepository.saveAll(recipeInstructionList);
    }

    @Override
    public Optional<RecipeInstruction> findById(Integer id) {
        return recipeInstructionRepository.findById(id).isPresent() ?
                Optional.of(recipeInstructionRepository.findById(id).get()) :
                Optional.empty();
    }

    @Override
    public Collection<RecipeInstruction> findAll() {
        return (Collection<RecipeInstruction>) recipeInstructionRepository.findAll();
    }

    @Override
    public RecipeInstruction update(RecipeInstruction recipeInstruction) {
        if(recipeInstruction == null ){
            throw new IllegalArgumentException ("recipeInstruction is null");
        }

        return save(recipeInstruction);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(id < 1 ){
            throw new IllegalArgumentException ("id is 0");
        }

        if(recipeInstructionRepository.existsById(id)){
            removeAssociatedEntity(id);
            recipeInstructionRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(RecipeInstruction recipeInstruction) {
        if(recipeInstruction == null ){
            throw new IllegalArgumentException ("recipeInstruction is null");
        }

        if(recipeInstructionRepository.existsById(recipeInstruction.getId())){
            removeAssociatedEntity(recipeInstruction.getId());
            recipeInstructionRepository.delete(recipeInstruction);
            return true;
        }

        return false;
    }

    private void removeAssociatedEntity(Integer id){
        List<Recipe> recipeList = recipeRepository.findAllByInstructionId(id);

        recipeList.forEach(recipe -> recipe.setInstruction(null));
    }

}
