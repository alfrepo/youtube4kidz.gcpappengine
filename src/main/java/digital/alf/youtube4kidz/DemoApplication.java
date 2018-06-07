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
        List<User> users = userDao.listUsers();
        List<Video> videos = videoDao.getVideos();
        return String.format("hello world! VideoResource: %s, Users: %s", videos.size(), users.size());
    }
}
