package recipe_application.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import recipe_application.application.data.service.RecipeInstructionService;
import recipe_application.application.dto.forms.recipeInstructionForm.CreateRecipeInstructionForm;
import recipe_application.application.dto.forms.recipeInstructionForm.UpdateRecipeInstructionForm;
import recipe_application.application.dto.views.RecipeInstructionView;

import javax.validation.Valid;
import java.util.Collection;


@Controller
@RequestMapping("/api/v1/recipe-instruction")
public class RecipeInstructionController {

    private final RecipeInstructionService recipeInstructionService;

    @Autowired
    public RecipeInstructionController(RecipeInstructionService recipeInstructionService) {
        this.recipeInstructionService = recipeInstructionService;
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<RecipeInstructionView>> getRecipeInstructionList() {
        return ResponseEntity.ok(recipeInstructionService.findAll());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<RecipeInstructionView> getRecipeInstructionById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(recipeInstructionService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<RecipeInstructionView> addRecipeInstruction(@Valid @RequestBody CreateRecipeInstructionForm createRecipeInstructionForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeInstructionService.save(createRecipeInstructionForm));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RecipeInstructionView> updateRecipeInstruction(@PathVariable("id") Integer id, @Valid @RequestBody UpdateRecipeInstructionForm updateRecipeInstructionForm) {
        RecipeInstructionView recipeInstructionView = recipeInstructionService.findById(id);

        updateRecipeInstructionForm.setId(recipeInstructionView.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(recipeInstructionService.update(updateRecipeInstructionForm));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        recipeInstructionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}



























