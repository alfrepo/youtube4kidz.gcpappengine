package digital.alf.youtube4kidz;

import digital.alf.youtube4kidz.user.UserDao;
import digital.alf.youtube4kidz.video.VideoDao;
import digital.alf.youtube4kidz.user.User;
import digital.alf.youtube4kidz.video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
