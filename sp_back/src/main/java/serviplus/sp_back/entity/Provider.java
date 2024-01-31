package serviplus.sp_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider extends Client {

    @NotNull(message = "Salary is requiered")
    private Double salary;
    @OneToOne
    private Category category;
    private Double rating;

}
