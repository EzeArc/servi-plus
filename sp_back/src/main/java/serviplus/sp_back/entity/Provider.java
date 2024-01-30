package serviplus.sp_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider extends Client {

    @NotBlank(message = "Salary is requiered")
    private Double salary;
    @NotBlank(message = "Category is requiered")
    @OneToOne
    private Category category;
    private Double rating;

}
