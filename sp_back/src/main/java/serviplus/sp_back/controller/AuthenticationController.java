package serviplus.sp_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import serviplus.sp_back.controller.models.AuthenticationRequest;
import serviplus.sp_back.controller.models.AuthenticationResponse;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.service.AuthenticationServiceImpl;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/registerClient")
    public ResponseEntity<AuthenticationResponse> registerClient (@RequestBody Client clientRequest) {
        return ResponseEntity.ok(authenticationService.registerClient(clientRequest));
    }

    @PostMapping("/registerProvider")
    public ResponseEntity<AuthenticationResponse> registerProvider (@RequestBody Provider providerRequest) {
        return ResponseEntity.ok(authenticationService.registerProvider(providerRequest));
    }

    @PostMapping("/authenticateClient")
    public ResponseEntity<AuthenticationResponse> authenticateClient (@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateClient(request));
    }

    @PostMapping("/authenticateProvider")
    public ResponseEntity<AuthenticationResponse> authenticateProvider (@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateProvider(request));
    }
}
