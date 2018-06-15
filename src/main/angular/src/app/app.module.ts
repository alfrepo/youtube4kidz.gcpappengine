import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {VideoItemComponent} from './video-item/video-item.component';
import {VideoService} from "./video/video.service";
import {HttpClientModule} from "@angular/common/http";
import {VideoOverlayComponent} from './video-overlay/video-overlay.component';
import {VideoOverlayService} from "./video-overlay/video-overlay.service";


@NgModule({
  declarations: [
    AppComponent,
    VideoItemComponent,
    VideoOverlayComponent
  ],
  imports: [
    BrowserModule, RouterModule, HttpClientModule
  ],
  providers: [VideoService, VideoOverlayService],
  bootstrap: [AppComponent]
})
export class AppModule { }
