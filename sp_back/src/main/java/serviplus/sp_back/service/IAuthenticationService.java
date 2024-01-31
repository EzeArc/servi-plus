package serviplus.sp_back.service;

import serviplus.sp_back.controller.models.AuthenticationRequest;
import serviplus.sp_back.controller.models.AuthenticationResponse;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Provider;

public interface IAuthenticationService {

    public AuthenticationResponse registerClient(Client clientRequest);
    public AuthenticationResponse registerProvider(Provider providerRequest);
    public AuthenticationResponse authenticateClient(AuthenticationRequest clientRequest);
    public AuthenticationResponse authenticateProvider(AuthenticationRequest providerRequest);

}
