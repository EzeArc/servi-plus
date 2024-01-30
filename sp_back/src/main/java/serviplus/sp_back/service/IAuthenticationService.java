package serviplus.sp_back.service;

import serviplus.sp_back.controller.models.AuthenticationRequest;
import serviplus.sp_back.controller.models.AuthenticationResponse;
import serviplus.sp_back.controller.models.RegisterRequest;

public interface IAuthenticationService {

    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);

}
