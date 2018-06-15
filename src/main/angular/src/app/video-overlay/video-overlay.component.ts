import {Component} from '@angular/core';
import {VideoOverlayService} from "./video-overlay.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-video-overlay',
  templateUrl: './video-overlay.component.html',
  styleUrls: ['./video-overlay.component.css']
})
export class VideoOverlayComponent {

  constructor(private videoOverlayService: VideoOverlayService,
              public sanitizer: DomSanitizer) {
  }

  deselectVideo() {
    this.videoOverlayService.deselectVideo()
  }

  getYoutubeId(): string {
    var youtubeId: string = "";

    if (this.videoOverlayService.getVideoSelected() != null) {
      youtubeId = this.videoOverlayService.getVideoSelected().youtubeId;
    }

    return youtubeId;
  }

}

// TODO: how to dynamically exchange
