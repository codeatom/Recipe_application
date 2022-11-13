package recipe_application.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_application.application.data.service.RecipeCategoryService;
import recipe_application.application.dto.forms.recipeCategoryForm.AddRecipeForm;
import recipe_application.application.dto.forms.recipeCategoryForm.CreateRecipeCategoryForm;
import recipe_application.application.dto.forms.recipeCategoryForm.UpdateRecipeCategoryForm;
import recipe_application.application.dto.views.RecipeCategoryView;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("/api/v1/recipe-category")
@CrossOrigin("*")
public class RecipeCategoryController {

    private final RecipeCategoryService recipeCategoryService;

    @Autowired
    public RecipeCategoryController(RecipeCategoryService recipeCategoryService) {
        this.recipeCategoryService = recipeCategoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<RecipeCategoryView>> getRecipeCategoryList() {
        return ResponseEntity.ok(recipeCategoryService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RecipeCategoryView> getRecipeCategoryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(recipeCategoryService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<RecipeCategoryView> addRecipeCategory(@Valid @RequestBody CreateRecipeCategoryForm createRecipeCategoryForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeCategoryService.save(createRecipeCategoryForm));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RecipeCategoryView> updateRecipeCategory(@PathVariable("id") Integer id, @Valid @RequestBody UpdateRecipeCategoryForm updateRecipeCategoryForm) {
        updateRecipeCategoryForm.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeCategoryService.update(updateRecipeCategoryForm));
    }

    @PostMapping("/add-recipe")
    public ResponseEntity<RecipeCategoryView> addRecipe(@Valid @RequestBody AddRecipeForm addRecipeForm) {
        recipeCategoryService.addRecipe(addRecipeForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(recipeCategoryService.addRecipe(addRecipeForm));
    }

    @GetMapping("/remove-recipe/{recipeCategoryId}/{recipeId}")
    public ResponseEntity<Void> removeRecipe(@PathVariable("recipeCategoryId") Integer recipeCategoryId, @PathVariable("recipeId") Integer recipeId) {
        recipeCategoryService.removeRecipe(recipeCategoryId, recipeId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        recipeCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
