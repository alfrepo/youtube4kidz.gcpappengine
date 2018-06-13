package digital.alf.youtube4kidz.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class VideoController {

    public static final String CONTROLLER_URL_MAPPING = "/api/videos";

    @Autowired
    VideoDao videoDao;

    @RequestMapping(method = GET, value = CONTROLLER_URL_MAPPING)
    public @ResponseBody ResponseEntity<?> video(
            @RequestParam(value = "author", required = false) String author) {

        List<Video> videos = videoDao.getVideos();

        // adding self links to the videos
        for (Video video : videos) {
            Link link = linkTo(VideoController.class).slash(CONTROLLER_URL_MAPPING).slash(video.getEntityId()).withSelfRel();
            video.add(link);
        }

        // self link to all videos
//        Link link = linkTo(VideoController.class).slash(CONTROLLER_URL_MAPPING).withSelfRel();
//        Resources<Video> resources = new Resources<Video>(videos, link);

        return ResponseEntity.ok(videos);
    }

    @RequestMapping(method = GET, value = CONTROLLER_URL_MAPPING + "/{videoId}")
    public @ResponseBody ResponseEntity<?> getAttr(@PathVariable(value = "videoId") String videoId,
                @RequestParam(required = false) String attr) {
        Long numericId = Long.parseLong(videoId);
        Video video =  videoDao.getById(numericId);

        // self link to all videos
        Link link = linkTo(VideoController.class).slash(CONTROLLER_URL_MAPPING).slash(videoId).withSelfRel();
        video.add(link);

        return ResponseEntity.ok(video);
    }
}
