package serviplus.sp_back.service;

import java.util.List;

import serviplus.sp_back.entity.Provider;

public interface IProviderService {

    public Provider getProvider(Long id);

    public List<Provider> listAllProvider();

    public List<Provider> listAllProviderActive();

    public Long countBy();

    public Provider updateProvider(Provider providerDB, Provider providerReceived);

    public Provider updateProviderStatus(Long id);
}
