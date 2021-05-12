package com.example.demo;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.AuthDao;
import com.example.demo.dao.RolesDao;
import com.example.demo.model.Admin;
import com.example.demo.model.Auth;
import com.example.demo.model.Roles;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
@EnableScheduling
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
//		populateRolesTable();
//		createAdmin();
	}

	@Autowired
	private static RolesDao rolesdao;

    @Autowired
    private static AdminDao admindao;
    @Autowired
    private static AuthDao authdao;
	
    @Transactional
	public static void populateRolesTable() {
		Roles role1 = new Roles("admin");
		Roles role2 = new Roles("doctor");
		Roles role3 = new Roles("user");
		rolesdao.save(role1);
		rolesdao.save(role2);
		rolesdao.save(role3);
	}
	@Transactional
	public static void createAdmin() {
		Auth auth=new Auth("admin@gmail.com","admin");
		Auth persisted_auth=authdao.save(auth);
		Admin admin=new Admin("Satpal","Singh","Male","1999-08-12","9747996321","748596000025",persisted_auth);
		admindao.save(admin);
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")).paths(PathSelectors.any())
				.build();
	}

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		String url = System.getenv("DB_HOST");
		if (url != null) {
			dataSourceBuilder.url("jdbc:mysql://mysql-db:3306/vaccinationdb?createDatabaseIfNotExist=true&useSSL=true");
		} else {
			dataSourceBuilder.url("jdbc:mysql://localhost:3306/vaccinationdb");
		}
		dataSourceBuilder.username("vaccinationdbuser");
		dataSourceBuilder.password("Vaccine@1");
		return dataSourceBuilder.build();
	}

}
