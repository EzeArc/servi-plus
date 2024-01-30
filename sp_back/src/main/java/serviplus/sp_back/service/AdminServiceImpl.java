package serviplus.sp_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import serviplus.sp_back.entity.Admin;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.enums.Role;
import serviplus.sp_back.repository.AdminRepository;
import serviplus.sp_back.repository.ClientRepository;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin getAdmin(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Admin> listAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Admin updateAdmin(Admin admin) {
        Admin adminDB = getAdmin(admin.getId());
        if (adminDB == null) {
            return null;
        }
        adminDB.setName(admin.getName());
        adminDB.setMail(admin.getMail());
        adminDB.setAddres(admin.getAddres());
        adminDB.setPhone(admin.getPhone());
        adminDB.setImage(admin.getImage());
        adminDB.setPassword(admin.getPassword());
        return adminRepository.save(adminDB);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Admin deleteAdmin(Long id) {
        Admin adminDB = getAdmin(id);
        if (adminDB == null) {
            return null;
        }
        adminDB.setState(true);
        return adminRepository.save(adminDB);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Admin changeRoleToAdmin(Long id) {
        Client clientDB = clientRepository.findById(id).orElse(null);
        Admin adminDB = adminRepository.findById(id).orElse(null);
        if (adminDB == null) {
            adminDB = new Admin();
            adminDB.setId(clientDB.getId());
            adminDB.setMail(clientDB.getMail());
            adminDB.setRol(Role.ADMIN);
            return adminRepository.save(adminDB);
        } else {
            adminDB.setRol(Role.ADMIN);
            return adminRepository.save(adminDB);
        }
    }
}
