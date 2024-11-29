package LawFirmApp;

import LawFirmApp.views.LawFirmView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import LawFirmApp.config.Database;
import LawFirmApp.views.LawFirmView;

@ComponentScan(basePackages = "LawFirmApp")
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        LawFirmView LawFirmView = applicationContext.getBean(LawFirmView.class);
        LawFirmView.run();
    }

    @Bean
    Database database() {
        Database database = new Database("lawfirm", "root", "", "localhost", "3306");
        database.setup();
        return database;
    }
}