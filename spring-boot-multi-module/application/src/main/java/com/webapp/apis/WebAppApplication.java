package com.webapp.apis;

import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableSwagger2
public class WebAppApplication implements WebMvcConfigurer {

	@Autowired
	private Environment environment;
	
	 @Autowired
	    private ServletContext servletContext;

	private static final Logger logger = LoggerFactory.getLogger(WebAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
		logger.info("Application Server Started........");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public CorsInterceptor corsInterceptor() {
		return new CorsInterceptor();
	}

	@Bean(name = "commonsMultipartResolver")
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	@Autowired
	@Qualifier("dataSource")
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/html/");
		resolver.setSuffix(".html");
		return resolver;
	}
	
	@Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost")
                .directModelSubstitute(LocalDate.class, Date.class)
                .pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return "/";
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
 
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WebApp API")
                .description("Demonstration of all write portal API")
                .version("1.0")
                .build();
    }

}
