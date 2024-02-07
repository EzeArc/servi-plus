package serviplus.sp_back.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import serviplus.sp_back.entity.Admin;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Provider;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:4321/")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody Admin adminRequest) {
        return ResponseEntity.ok(authenticationService.registerAdmin(adminRequest));
    }

    @PostMapping("/registerClient")
    public ResponseEntity<AuthResponse> registerClient(@RequestBody Client clientRequest) {
        return ResponseEntity.ok(authenticationService.registerClient(clientRequest));
    }

    @PostMapping( value = "/registerProvider", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<AuthResponse> registerProvider(@RequestPart Provider providerRequest,
            @RequestPart("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(authenticationService.registerProvider(providerRequest, file));
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes devolver una respuesta de error más específica según tus necesidades.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticateLogin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateLogin(request));
    }
}
