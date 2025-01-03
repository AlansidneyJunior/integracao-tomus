package br.com.tomus.projeto.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ComponentScan(basePackages = {"br.com.tomus.projeto.managedBeans", 
								"br.com.tomus.projeto.models",
								"br.com.tomus.projeto.dao"})
public class AppConfig {
}
