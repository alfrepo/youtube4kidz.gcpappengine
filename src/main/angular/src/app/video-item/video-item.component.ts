import {Component, HostListener, Input, OnInit} from '@angular/core';
import {Video} from "../video/video";
import {VideoService} from "../video/video.service";
import {VideoOverlayService} from "../video-overlay/video-overlay.service";

@Component({
  selector: 'app-video-item',
  templateUrl: './video-item.component.html',
  styleUrls: ['./video-item.component.css']
})
export class VideoItemComponent{

  @Input() video: Video  // <-- added Input annotation

  constructor(private videoOverlayService: VideoOverlayService) {
  }

  ngOnInit() {
  }

  private selectVideo(video: Video): void {
    this.videoOverlayService.selectVideo(video)
  }

}
