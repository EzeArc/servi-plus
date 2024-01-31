package serviplus.sp_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.repository.ProviderRepository;

@Service
public class ProviderServiceImpl implements IProviderService {

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
    public Provider updateProvider(Provider provider) {
        Provider providerDB = getProvider(provider.getId());
        if (providerDB == null) {
            return null;
        }
        providerDB.setName(provider.getName());
        providerDB.setMail(provider.getMail());
        providerDB.setAddres(provider.getAddres());
        providerDB.setPhone(provider.getPhone());
        providerDB.setImage(provider.getImage());
        providerDB.setPassword(provider.getPassword());
        providerDB.setCategory(provider.getCategory());
        providerDB.setSalary(provider.getSalary());
        return providerRepository.save(providerDB);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Provider deleteUser(Long id) {
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

}
