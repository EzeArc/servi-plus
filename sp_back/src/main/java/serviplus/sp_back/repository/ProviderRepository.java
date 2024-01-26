package serviplus.sp_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import serviplus.sp_back.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    public Provider findByMail(String mail);
}
