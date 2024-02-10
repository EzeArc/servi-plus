package serviplus.sp_back.service;

import java.util.List;

import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.entity.ProviderDTO;

public interface IProviderService {

    public List<Provider> listAllProvider();

    public List<Provider> listAllProviderActive();

    public List<ProviderDTO> getAllProvidersWithImagesDTO();

    public Long countBy();

    public Provider getProvider(Long id);

    public Provider updateProvider(Provider providerDB, Provider providerReceived);

    public Provider updateProviderStatus(Long id);
}
