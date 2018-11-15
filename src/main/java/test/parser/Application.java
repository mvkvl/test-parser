package test.parser;

import static java.lang.System.exit;

import java.util.Arrays;
import java.util.List;

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
    	List<String> inputFileNames = Arrays.asList(args);
    	inputFileNames.parallelStream()
    				  .forEach(fileParserService::parse);
        exit(0);
    }

}



//FileUtils.listFiles(new File("."), new WildcardFileFilter("*input*"), null)
//.parallelStream()
//.forEach(f -> fileParserService.parse(f.getName()));
//List<File> list = Arrays.asList(new File("input001.json"));
//list.stream().forEach(f -> fileParserService.parse(f.getName()));

