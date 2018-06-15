import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-video-item',
  templateUrl: './video-item.component.html',
  styleUrls: ['./video-item.component.css']
})
export class VideoItemComponent implements OnInit {

  @Input() youtubeId: string // <-- added Input annotation

  constructor() { }

  ngOnInit() {
  }

}
