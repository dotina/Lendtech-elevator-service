package com.lendtech.mselevator.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigProperties {

    @Value("${app-properties.api-accepted-apps}")
    private String acceptedApps;

    @Value("${app-properties.api-username}")
    private String basicAuthUsername;

    @Value("${app-properties.api-password}")
    private String basicAuthPassword;

    public String getAcceptedApps() {
        return acceptedApps;
    }

    public void setAcceptedApps(String acceptedApps) {
        this.acceptedApps = acceptedApps;
    }

    public String getBasicAuthUsername() {
        return basicAuthUsername;
    }

    public void setBasicAuthUsername(String basicAuthUsername) {
        this.basicAuthUsername = basicAuthUsername;
    }

    public String getBasicAuthPassword() {
        return basicAuthPassword;
    }

    public void setBasicAuthPassword(String basicAuthPassword) {
        this.basicAuthPassword = basicAuthPassword;
    }
}