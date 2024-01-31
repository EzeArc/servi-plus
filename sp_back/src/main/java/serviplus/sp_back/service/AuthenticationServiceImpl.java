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
import serviplus.sp_back.entity.Category;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.enums.Role;
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
    public AuthenticationResponse registerClient(Client clientRequest) {
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
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse registerProvider(Provider providerRequest) {
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
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticateClient(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        var user = clientRepository.findByMail(request.getMail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticateProvider(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        var user = providerRepository.findByMail(request.getMail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
