package serviplus.sp_back.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.var;
import serviplus.sp_back.config.JwtService;
import serviplus.sp_back.controller.models.AuthenticationRequest;
import serviplus.sp_back.controller.models.AuthenticationResponse;
import serviplus.sp_back.controller.models.RegisterRequest;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.enums.Role;
import serviplus.sp_back.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        // Convertir el String a Role
        Role requestRole = Role.valueOf(request.getRol());
    
        var user = Client.builder()
                .name(request.getName())
                .mail(request.getMail())
                .addres(request.getAddres())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(requestRole) // Asignar el Role convertido desde la solicitud
                .build();
    
        clientRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    
    

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        var user = clientRepository.findByMail(request.getMail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
