package serviplus.sp_back.service;

import java.util.List;

import serviplus.sp_back.entity.Admin;

public interface IAdminService {
    public Admin getAdmin(Long id);

    public List<Admin> listAllAdmin();

    public Admin changeRoleToAdmin(Long id);

    public Admin createAdmin(Admin admin);

    public Admin updateAdmin(Admin admin);

    public Admin deleteAdmin(Long id);
}
