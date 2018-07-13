import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {VideoService} from "../video/video.service";

@Injectable()
export class ToolService {

  constructor() {
  }

  backendUrl(): string {
    var url: string = environment.backendUrl;
    if (environment.backendPort) {
      url = url + ':' + environment.backendPort
    }
    return url;
  }

}
