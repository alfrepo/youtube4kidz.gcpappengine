import {EventEmitter, Injectable, Output} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {Video} from "../video/video";

@Injectable()
export class VideoOverlayService {

  selectedVideo:Video =null;

  @Output() change: EventEmitter<Video> = new EventEmitter<Video>();

  selectVideo(video:Video){
    this.selectedVideo = video;
    this.change.emit(video);
  }

  deselectVideo(){
    this.selectedVideo = null;
    this.change.emit(null);
  }

  getVideoSelected():Video{
    return this.selectedVideo;
  }

  isVideoSelected():boolean{
    return this.selectedVideo!=null;
  }

  constructor() {
  }

}
