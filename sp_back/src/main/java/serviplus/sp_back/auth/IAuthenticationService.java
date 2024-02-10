package serviplus.sp_back.auth;

import org.springframework.web.multipart.MultipartFile;

import serviplus.sp_back.entity.Admin;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Provider;

public interface IAuthenticationService {

    public AuthResponse registerAdmin(Admin adminRequest);
    public AuthResponse registerClient(Client clientRequest);
    public AuthResponse registerProvider(Provider providerRequest, MultipartFile file);
    public AuthResponse authenticateLogin(LoginRequest loginRequest);

}
