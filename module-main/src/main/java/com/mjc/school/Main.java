package com.mjc.school;

import com.mjc.school.menu.MenuRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = {"com.mjc.school.service",
        "com.mjc.school.repository",
        "com.mjc.school.controller",
        "com.mjc.school"})
@EnableAspectJAutoProxy
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        MenuRunner menu = context.getBean(MenuRunner.class);
        menu.setScanner(new Scanner(System.in));
        menu.runMenu();
    }
}
