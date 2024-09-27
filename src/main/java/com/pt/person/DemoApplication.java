package com.pt.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

import org.h2.tools.Server;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {

    try {
      // Iniciar o servidor H2 com o console habilitado para conexões remotas
      Server.createWebServer("-web", "-webAllowOthers", "-tcpAllowOthers", "-browser").start();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    SpringApplication.run(DemoApplication.class, args);
  }

}