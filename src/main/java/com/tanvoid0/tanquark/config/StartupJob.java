package com.tanvoid0.tanquark.config;


import com.tanvoid0.tanquark.models.user.role.RoleRepository;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ConfigUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Slf4j
public class StartupJob {

    @Inject
    RoleRepository roleRepository;

    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    String jdbcUrl;

    void onStart(@Observes StartupEvent event) {
        final String profile = ConfigUtils.getProfiles().toString();
        log.info("My Quarkus application is starting up in {} Profile...", profile);
        if (!profile.toLowerCase().startsWith("prod")) {
            log.debug("Database url: {}", jdbcUrl);
        }
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
