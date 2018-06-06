package digital.alf.youtube4kidz.services;

import digital.alf.youtube4kidz.data.daos.VideoDao;
import digital.alf.youtube4kidz.data.objects.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class VideoController {

    public static final String URL_MAPPING = "videos";

    @Autowired
    VideoDao videoDao;

    @RequestMapping(URL_MAPPING)
    //    public HttpEntity<VideoResource> video(
    public Resources<Video> video(
            @RequestParam(value = "author", required = false) String author) {

        List<Video> videos = videoDao.getVideos();

        // adding self links to the videos
        for (Video video : videos) {
            Link link = linkTo(VideoController.class).slash(URL_MAPPING).slash(video.getVideoId()).withSelfRel();
            video.add(link);
        }

        // self link to all videos
        Link link = linkTo(VideoController.class).slash(URL_MAPPING).withSelfRel();

        Resources<Video> resources = new Resources<Video>(videos, link);
        return resources;
    }

    @RequestMapping(URL_MAPPING + "/{videoId}")
    public @ResponseBody Resource getAttr(@PathVariable(value = "videoId") String videoId,
                @RequestParam(required = false) String attr) {
        Long numericId = Long.parseLong(videoId);
        Video video =  videoDao.getById(numericId);

        // self link to all videos
        Link link = linkTo(VideoController.class).slash(URL_MAPPING).slash(video.getVideoId()).withSelfRel();
        video.add(link);

        return new Resource<Video>(video);
    }
}
