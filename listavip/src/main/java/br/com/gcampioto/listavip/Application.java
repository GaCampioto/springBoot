package br.com.gcampioto.listavip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by gcampioto on 11/04/17.
 */
@SpringBootApplication
public class Application {

    public static void main(String [] args){
        SpringApplication.run(Application.class, args);
    }
}
