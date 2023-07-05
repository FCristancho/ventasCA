package co.com.devco.jpa.config;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static co.com.devco.jpa.utils.Messages.SECRETO_NO_ENCONTRADO;

@Configuration
public class JpaConfig {

    final Logger log = Logger.getLogger("co.com.devco.jpa.config.JpaConfig");

    @Autowired
    private AWSSecretsManager secretsManager;

    @Bean
    public DBSecret dbSecret(Environment env) {
        JsonNode secret = this.getSecretValue(env);
        return DBSecret.builder()
                .url(secret.get("url").textValue())
                .username(secret.get("username").textValue())
                .password(secret.get("password").textValue())
                .build();
    }

    @Bean
    public DataSource datasource(DBSecret secret, @Value("${spring.datasource.driverClassName}") String driverClass) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(secret.getUrl());
        config.setUsername(secret.getUsername());
        config.setPassword(secret.getPassword());
        config.setDriverClassName(driverClass);
        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            @Value("${spring.jpa.databasePlatform}") String dialect) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("co.com.devco.jpa");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        em.setJpaProperties(properties);

        return em;
    }

    private JsonNode getSecretValue(Environment env) {
        String secretName = env.getProperty("cloud.aws.secret-name");
        try  {
            GetSecretValueRequest getSecretValueRequest  =  new GetSecretValueRequest().withSecretId(secretName);
            secretsManager.getSecretValue(getSecretValueRequest);
            ObjectMapper objectMapper  =  new  ObjectMapper();
            String secretValue= secretsManager.getSecretValue(getSecretValueRequest).getSecretString();
            return objectMapper.readTree(secretValue);
        } catch(Exception e) {
            log.log(Level.WARNING, String.format(SECRETO_NO_ENCONTRADO, secretName, e));
            return null;
        }
    }
}
