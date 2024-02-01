package serviplus.sp_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import serviplus.sp_back.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
