package serviplus.sp_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import serviplus.sp_back.entity.Category;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Job;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.repository.ClientRepository;
import serviplus.sp_back.repository.JobRepository;
import serviplus.sp_back.repository.ProviderRepository;

@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ClientRepository clientRepository;

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
    public Job createJob(Long idProvider, Long idClient) {
        try {
            Optional<Client> responseClient = clientRepository.findById(idClient);
            if (responseClient.isPresent()) {
                Client clientDB = responseClient.get();

                Optional<Provider> responseProvider = providerRepository.findById(idProvider);
                if (responseProvider.isPresent()) {
                    Provider providerDB = responseProvider.get();

                    Category categoryDB = categoryServiceImpl.getCategory(providerDB.getCategory().getId());

                    Job job = new Job();
                    job.setStatus(false);
                    job.setProvider(providerDB);
                    job.setClient(clientDB);
                    job.setCategory(categoryDB);
                    job.setJobStatus(false);

                    return jobRepository.save(job);
                } else {
                    throw new EntityNotFoundException("Provider not found with ID: " + idProvider);
                }
            } else {
                throw new EntityNotFoundException("Client not found with ID: " + idClient);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error creating a job. Details: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Job updateJobStatus(Long id) {
        Job jobDB = getJob(id);
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
    public List<Job> listAllJobToCalificate() {
        return jobRepository.findByJobStatusTrue();
    }

    @Override
    public List<Job> listAllJobToFinish() {
        return jobRepository.findByJobStatusAndStatus(false, false);
    }

    @Override
    public Long countByAllJob() {
        return jobRepository.countBy();
    }

}
