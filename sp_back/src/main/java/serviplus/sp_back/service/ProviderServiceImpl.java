package serviplus.sp_back.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;
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
    public Provider createProvider(Provider provider) {
        provider.setState(false);
        return providerRepository.save(provider);
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

        @Override
    public UserDetails loadUserByMail(String mail) throws UsernameNotFoundException {
        Provider providerDB = providerRepository.findByMail(mail);
        if (providerDB == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + mail);
        }
        List<GrantedAuthority> permission = new ArrayList<>();
        GrantedAuthority providerPermission = new SimpleGrantedAuthority("ROLE_" + providerDB.getRol().toString());
        permission.add(providerPermission);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("providerSession", providerDB);
        return new User(providerDB.getMail(), providerDB.getPassword(), permission);
    }
}
