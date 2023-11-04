package com.tanvoid0.tanquark.config;


import com.tanvoid0.tanquark.models.user.role.RoleRepository;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class StartupJob {

    @Inject
    RoleRepository roleRepository;

    void onStart(@Observes StartupEvent event) {
        log.info("My Quarkus application is starting up...");
        // Add your startup logic here
        run();
    }

    void onStop(@Observes ShutdownEvent event) {
        log.info("My Quarkus application is shutting down...");
        // Add your shutdown logic here
    }

    @Transactional
    public void run() {
        log.info("Startup job started");
        roleRepository.startupJob();
    }
}
