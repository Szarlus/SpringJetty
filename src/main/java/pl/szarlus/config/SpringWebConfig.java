package pl.szarlus.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by karol on 26.11.2017.
 */
@Configuration
@ComponentScan(basePackages = "pl.szarlus")
@EnableWebMvc
public class SpringWebConfig extends WebMvcConfigurerAdapter{
}
