package serviplus.sp_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import serviplus.sp_back.enums.Role;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is requiered")
    private String name;
    @NotBlank(message = "Addres is requiered")
    private String addres;
    @NotBlank(message = "Phone is requiered")
    private String phone;
    @NotBlank(message = "Mail is requiered")
    private String mail;
    @Size(min = 4)
    private String password;
    private boolean state;
    @NotNull
    @OneToOne
    private Image image;

    @Enumerated(EnumType.STRING)
    private Role rol;
}
