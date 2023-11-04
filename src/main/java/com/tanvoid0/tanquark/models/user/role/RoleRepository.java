package com.tanvoid0.tanquark.models.user.role;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
@Slf4j
public class RoleRepository implements PanacheRepository<Role> {

    public Optional<Role> findByName(final ERole name) {
        return find("name", name).firstResultOptional();
    }

    public void startupJob() {
        Set<ERole> roles = ERole.getAllAsSet();
        Set<String> newRoles = new HashSet<>();
        roles.forEach(role -> {
            final Optional<Role> roleEntity = this.findByName(role);
            if (roleEntity.isEmpty()) {
                final Role newRole = new Role(role, null);

                this.persist(newRole);
                newRoles.add(newRole.getName().name());
            }
        });
        log.info("Total {} roles. new roles inserted '{}'", roles.size(), String.join(", ", newRoles));
    }
}
