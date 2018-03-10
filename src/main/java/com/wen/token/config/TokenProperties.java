package com.wen.token.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:token.properties")
@ConfigurationProperties(prefix = "redis")
public class TokenProperties {

    public String sha;

    public String getSha() {
        return sha;
    }

    public void setSha(String tokenSha) {
        this.sha = tokenSha;
    }
    private long expires;
    private String subject;

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}