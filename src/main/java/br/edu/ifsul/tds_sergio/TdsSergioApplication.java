package br.edu.ifsul.tds_sergio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TdsSergioApplication {

    public static void main(String[] args) {
        System.out.println("NOVO HASH: " + new BCryptPasswordEncoder().encode("123"));

        SpringApplication.run(TdsSergioApplication.class, args);
    }
}