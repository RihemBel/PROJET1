package com.example.ecommerce.Web.rest;

import com.example.ecommerce.Service.RoleService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Role;
import com.example.ecommerce.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final Logger log = LoggerFactory.getLogger(LivraisonController.class);
    private static final String ENTITY_NAME = "mywaybaseRole";
    private String applicationName;
    private final RoleService roleService;

    public RoleController( RoleService roleService) {
        this.roleService = roleService;
    }



    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) throws URISyntaxException {
        log.debug("REST request to save Role : {}", role);
        if (role.getId() != null) {
            throw new BadRequestAlertException("A new role cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Role result = roleService.save(role);
        return ResponseEntity.created(new URI("/api/roles/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
}
