package serviplus.sp_back.service;

import java.util.List;

import serviplus.sp_back.entity.Category;
import serviplus.sp_back.entity.Job;
import serviplus.sp_back.entity.Provider;

public interface IJobService {
    public Job getJob(Long id);

    public Long countByAllJob();

    public List<Job> listAllJob();

    public List<Job> listAllJobByCategory(Category category);

    public List<Job> listAllJobByProvider(Provider provider);

    public List<Job> listAllJobToCalificate();

    public Job createJob(Job job);

    public Job updateJob(Job job);

    public Job deleteJob(Long id);
}
