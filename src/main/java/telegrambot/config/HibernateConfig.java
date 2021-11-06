package telegrambot.config;

import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

// Для меня: @Configuration, @Bean - java-конфигурирование Spring'а
// Для меня: @Component - автоматическое конфигурирование Spring'а
@Configuration
@ComponentScan("telegrambot")
@PropertySource(value = "classpath:dbsecret.properties")
public class HibernateConfig{

    //для того, чтобы получить свойства из property файла
    @Getter
    private Environment environment;

    @Autowired
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("telegrambot.model");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        SessionFactory sessionFactory = sessionFactoryBean.getObject();
        return sessionFactory;
    }
}
