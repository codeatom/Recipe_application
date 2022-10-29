package recipe_application.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import recipe_application.application.data.service.RecipeCategoryService;
import recipe_application.application.dto.forms.recipeCategoryForm.CreateRecipeCategoryForm;
import recipe_application.application.dto.forms.recipeCategoryForm.UpdateRecipeCategoryForm;
import recipe_application.application.dto.views.RecipeCategoryView;

import javax.validation.Valid;
import java.util.Collection;


@Controller
@RequestMapping("/api/v1/recipe-category")
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        recipeCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
