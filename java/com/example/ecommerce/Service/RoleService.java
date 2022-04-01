package com.example.ecommerce.Service;


import com.example.ecommerce.entities.Role;
import com.example.ecommerce.entities.RoleName;
import com.example.ecommerce.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    /**
     * Save a role.
     *
     * @param role the entity to save.
     * @return the persisted entity.
     */
    public Role save(Role role){

        return roleRepository.save(role);
    }

    public void createRoles() {

        List<Role> roleList = roleRepository.findAll();
        if (roleList.size() == 0) {
            //ADMIN
            Role ADMIN = new Role();
            //ADMIN.setId(UUID.fromString("b6c4042e-af50-11ec-b909-0242ac120002"));
            ADMIN.setRoleName(RoleName.ROLE_ADMIN);

            roleRepository.save(ADMIN);

            //CLIENT
            Role CLIENT = new Role();
            //ADMIN.setId(UUID.fromString("d13468d0-5e40-4944-bc8a-52ca2bd84adc"));
            CLIENT.setRoleName(RoleName.ROLE_CLIENT);

            roleRepository.save(CLIENT);

            //INTERNAUTE
            Role INTERNAUTE = new Role();
            //ADMIN.setId(UUID.fromString("ba769c1c-79c3-4ab7-b9d5-87b31d39415b"));
            INTERNAUTE.setRoleName(RoleName.ROLE_INTERNAUTE);

            roleRepository.save(INTERNAUTE);

        }
    }

}
