package serviplus.sp_back.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.var;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.enums.Role;
import serviplus.sp_back.jwt.JwtService;
import serviplus.sp_back.repository.ClientRepository;
import serviplus.sp_back.repository.ProviderRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final ClientRepository clientRepository;
    private final ProviderRepository providerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse registerClient(Client clientRequest) {
        Client clientRegister = Client.builder()
                .name(clientRequest.getName())
                .mail(clientRequest.getMail())
                .addres(clientRequest.getAddres())
                .phone(clientRequest.getPhone())
                .state(false)
                .password(passwordEncoder.encode(clientRequest.getPassword()))
                .rol(Role.USER)
                .build();

        clientRepository.save(clientRegister);
        var jwtToken = jwtService.generateToken(clientRegister);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse registerProvider(Provider providerRequest) {
        Provider providerRegister = new Provider();
                providerRegister.setName(providerRequest.getName());
                providerRegister.setMail(providerRequest.getMail());
                providerRegister.setAddres(providerRequest.getAddres());
                providerRegister.setPhone(providerRequest.getPhone());
                providerRegister.setCategory(providerRequest.getCategory());
                providerRegister.setSalary(providerRequest.getSalary());
                providerRegister.setImage(providerRequest.getImage());
                providerRegister.setState(false);
                providerRegister.setPassword(passwordEncoder.encode(providerRequest.getPassword()));
                providerRegister.setRol(Role.PROVIDER);
                providerRepository.save(providerRegister);
        var jwtToken = jwtService.generateToken(providerRegister);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticateLogin(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getMail(), loginRequest.getPassword()));
        UserDetails user = clientRepository.findByMail(loginRequest.getMail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
