package recipe_application.application.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RECIPEINSTRUCTION")
public class RecipeInstruction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "instruction")
    private String instruction;

    public RecipeInstruction() {
    }

    public RecipeInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Integer getId() {
        return id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeInstruction that = (RecipeInstruction) o;
        return Objects.equals(id, that.id) && Objects.equals(instruction, that.instruction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instruction);
    }

    @Override
    public String toString() {
        return "RecipeInstruction{" +
                "id=" + id +
                ", instruction='" + instruction + '\'' +
                '}';
    }

}
