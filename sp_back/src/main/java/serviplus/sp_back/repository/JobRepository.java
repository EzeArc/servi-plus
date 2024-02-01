package serviplus.sp_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import serviplus.sp_back.entity.Category;
import serviplus.sp_back.entity.Job;
import serviplus.sp_back.entity.Provider;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    public Long countBy();

    public List<Job> findByCategory(Category category);

    public List<Job> findByProvider(Provider provider);

    public List<Job> findByJobStatusAndStatus(boolean jobStatus, boolean status);

    public List<Job> findByJobStatusTrue();
}
