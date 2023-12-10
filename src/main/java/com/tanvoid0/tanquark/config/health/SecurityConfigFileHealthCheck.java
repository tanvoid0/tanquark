package com.tanvoid0.tanquark.config.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SecurityConfigFileHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resourceURL = classLoader.getResource("publicKey.pem");
            assert resourceURL != null;
            URI resourceURI = null;
            resourceURI = resourceURL.toURI();
            Path resourcePath = Paths.get(resourceURI);
            return HealthCheckResponse.up("Security Config working");
        } catch (URISyntaxException e) {
            return HealthCheckResponse.down("pem file not found");
        }
    }
}
