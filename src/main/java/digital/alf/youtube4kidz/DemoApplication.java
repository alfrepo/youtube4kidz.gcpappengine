package digital.alf.youtube4kidz;

import com.google.cloud.datastore.Datastore;
import digital.alf.youtube4kidz.data.daos.UserDao;
import digital.alf.youtube4kidz.data.daos.VideoDao;
import digital.alf.youtube4kidz.data.objects.User;
import digital.alf.youtube4kidz.data.objects.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

    @Autowired
    UserDao userDao;

    @Autowired
    VideoDao videoDao;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "hello world!" ;
    }
}
