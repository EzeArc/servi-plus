package serviplus.sp_back.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import serviplus.sp_back.entity.Provider;

public interface IProviderService {

    public Provider getProvider(Long id);

    public List<Provider> listAllProvider();

    public Long countBy();

    public Provider createProvider(Provider provider);

    public Provider updateProvider(Provider provider);

    public Provider deleteUser(Long id);

    public UserDetails loadUserByMail(String mail);
}
