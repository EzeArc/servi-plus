package serviplus.sp_back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import serviplus.sp_back.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Optional<Provider> findByMail(String mail);

    List<Provider> findByState(boolean state);
}
