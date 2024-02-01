package serviplus.sp_back.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.repository.ProviderRepository;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements IProviderService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider getProvider(Long id) {
        return providerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Provider> listAllProvider() {
        return providerRepository.findAll();
    }

    @Override
    @Transactional
    public Provider updateProvider(Provider providerDB, Provider providerReceived) {
        if (providerDB == null) {
            return null;
        }
        providerDB.setName(providerReceived.getName());
        providerDB.setMail(providerReceived.getMail());
        providerDB.setAddress(providerReceived.getAddress());
        providerDB.setPhone(providerReceived.getPhone());
        providerDB.setImage(providerReceived.getImage());
        providerDB.setPassword(passwordEncoder.encode(providerReceived.getPassword()));
        return providerRepository.save(providerDB);
    }

    @Override
    @Transactional
    public Provider updateProviderStatus(Long id) {
        Provider providerDB = getProvider(id);
        if (providerDB == null) {
            return null;
        }
        providerDB.setState(true);
        return providerRepository.save(providerDB);
    }

    @Override
    public Long countBy() {
        return providerRepository.count();
    }

    @Override
    public List<Provider> listAllProviderActive() {
        return providerRepository.findByState(false);
    }

}
