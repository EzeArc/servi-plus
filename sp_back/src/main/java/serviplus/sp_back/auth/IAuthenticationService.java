package serviplus.sp_back.auth;

import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Provider;

public interface IAuthenticationService {

    public AuthResponse registerClient(Client clientRequest);
    public AuthResponse registerProvider(Provider providerRequest);
    public AuthResponse authenticateLogin(LoginRequest loginRequest);

}
