package recipe_application.application.dto.views;

import java.util.Objects;


public class RecipeInstructionView {

    private final Integer id;
    private final String title;
    private final String instruction;

    public RecipeInstructionView(Integer id, String title, String instruction) {
        this.id = id;
        this.title = title;
        this.instruction = instruction;
    }

    public Integer getId() {
        return id;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeInstructionView that = (RecipeInstructionView) o;
        return Objects.equals(id, that.id) && Objects.equals(instruction, that.instruction) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instruction, title);
    }
}
