package serviplus.sp_back.controller.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String addres;
    private String phone;
    private String mail;
    private String password;
    private String rol;
}
