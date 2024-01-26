package serviplus.sp_back.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import serviplus.sp_back.entity.Category;
import serviplus.sp_back.entity.Job;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.repository.JobRepository;

@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job getJob(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public List<Job> listAllJob() {
        return jobRepository.findAll();
    }

    @Override
    @Transactional
    public Job createJob(Job job) {
        Provider provider = new Provider();
        Client client = new Client();
        job.setStatus(false);
        job.setProvider(provider);
        job.setClient(client);
        job.setJobStatus(false);
        return jobRepository.save(job);
    }

    @Override
    @Transactional
    public Job updateJob(Job job) {
        Job jobDB = getJob(job.getId());
        if (jobDB == null) {
            return null;
        }
        jobDB.setJobStatus(true);
        return jobRepository.save(jobDB);
    }

    @Override
    @Transactional
    public Job deleteJob(Long id) {
        Job jobDB = getJob(id);
        if (jobDB == null) {
            return null;
        }
        jobDB.setStatus(true);
        return jobRepository.save(jobDB);
    }

    @Override
    public List<Job> listAllJobByCategory(Category category) {
        return jobRepository.findByCategory(category);
    }

    @Override
    public List<Job> listAllJobByProvider(Provider provider) {
        return jobRepository.findByProvider(provider);
    }

    @Override
    public Long countByAllJob() {
        return jobRepository.countBy();
    }

    @Override
    public List<Job> listAllJobToCalificate() {
        return jobRepository.findByJobStatusAndStatus(true, false);
    }

}
