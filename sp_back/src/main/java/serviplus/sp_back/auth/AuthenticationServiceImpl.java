package serviplus.sp_back.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.var;
import serviplus.sp_back.entity.Admin;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Image;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.enums.Role;
import serviplus.sp_back.jwt.JwtService;
import serviplus.sp_back.repository.AdminRepository;
import serviplus.sp_back.repository.ClientRepository;
import serviplus.sp_back.repository.ProviderRepository;
import serviplus.sp_back.service.ImageServiceImpl;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final ProviderRepository providerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ImageServiceImpl imageServiceImpl;

    @Override
    public AuthResponse registerAdmin(Admin adminRequest) {
        Admin adminRegister = new Admin();
        adminRegister.setName(adminRequest.getName());
        adminRegister.setMail(adminRequest.getMail());
        adminRegister.setAddress(adminRequest.getAddress());
        adminRegister.setPhone(adminRequest.getPhone());
        adminRegister.setState(false);
        adminRegister.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
        adminRegister.setRol(Role.ADMIN);

        adminRepository.save(adminRegister);
        var jwtToken = jwtService.generateToken(adminRegister);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse registerClient(Client clientRequest) {
        Client clientRegister = Client.builder()
                .name(clientRequest.getName())
                .mail(clientRequest.getMail())
                .address(clientRequest.getAddress())
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
    public AuthResponse registerProvider(Provider providerRequest, MultipartFile file) {
        Provider providerRegister = new Provider();
        providerRegister.setName(providerRequest.getName());
        providerRegister.setMail(providerRequest.getMail());
        providerRegister.setAddress(providerRequest.getAddress());
        providerRegister.setPhone(providerRequest.getPhone());
        providerRegister.setCategory(providerRequest.getCategory());
        providerRegister.setSalary(providerRequest.getSalary());

        // Guarda la imagen y obtén la referencia
        Image savedImage = imageServiceImpl.saveImage(file);
        System.out.println(savedImage);
        providerRegister.setImage(savedImage);

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
