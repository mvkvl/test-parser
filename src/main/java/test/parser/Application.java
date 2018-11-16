package test.parser;

import static java.lang.System.exit;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import test.parser.service.FileParserService;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private FileParserService fileParserService;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    public void run(String... args) throws Exception {
    	Arrays.asList(args).parallelStream()
    				       .forEach(fileParserService::parse);
        exit(0);
    }

}
