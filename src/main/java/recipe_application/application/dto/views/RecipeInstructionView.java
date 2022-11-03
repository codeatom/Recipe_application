package recipe_application.application.dto.views;


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

    public String getTitle() {
        return title;
    }

    public String getInstruction() {
        return instruction;
    }
}
