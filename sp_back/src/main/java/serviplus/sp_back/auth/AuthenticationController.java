package serviplus.sp_back.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import serviplus.sp_back.entity.Admin;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Provider;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthResponse> registerAdmin (@RequestBody Admin adminRequest) {
        return ResponseEntity.ok(authenticationService.registerAdmin(adminRequest));
    }
    
    @PostMapping("/registerClient")
    public ResponseEntity<AuthResponse> registerClient (@RequestBody Client clientRequest) {
        return ResponseEntity.ok(authenticationService.registerClient(clientRequest));
    }

    @PostMapping("/registerProvider")
    public ResponseEntity<AuthResponse> registerProvider (@RequestBody Provider providerRequest) {
        return ResponseEntity.ok(authenticationService.registerProvider(providerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticateLogin (@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateLogin(request));
    }
}
