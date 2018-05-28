package digital.alf.youtube4kidz;

import digital.alf.youtube4kidz.data.DatastoreInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class DemoApplication {

    @Autowired
    DatastoreInitializer datastoreInitializer;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        datastoreInitializer.initDataStore();
        return "hello world2! " ;
    }
}
