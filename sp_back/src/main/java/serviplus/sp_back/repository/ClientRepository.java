package serviplus.sp_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import serviplus.sp_back.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Long countBy();

    Optional<Client> findByMail(String mail);
}
