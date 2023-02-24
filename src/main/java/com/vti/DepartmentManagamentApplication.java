package com.vti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vti.entities.Account;
import com.vti.form.AccountCreateForm;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DepartmentManagamentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartmentManagamentApplication.class, args);
    }

    // @Bean để thêm ModelMapper vào IoC Container
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(AccountCreateForm.class, Account.class)
                .addMappings(mapper -> mapper.skip(Account::setId));
        return modelMapper;
    }

    // Convert Object to String
    @Bean
    public ObjectWriter objectWriter() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .writerWithDefaultPrettyPrinter();
    }

    // Tạo CrossOrigin
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
//              mapping đến mọi đường dẫn, API
                registry.addMapping("/**")
//                       cho phép toàn bộ port được truy cập
                        .allowedOrigins("*")
//                       những phương thức được thực hiện
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
