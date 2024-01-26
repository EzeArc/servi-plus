package serviplus.sp_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import serviplus.sp_back.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
