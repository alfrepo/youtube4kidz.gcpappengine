import { Component } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  result = '';

  constructor(private http: Http){
  }

  private getVideos(): void {
    this.result = 'loading...';

    // TODO fix the hard coded url to the backend
    // the problem here is - the backend may run on another port, than the frontend.
    // learn to dynamicall find out, on which port the BE is running
    this.http.get(`http://localhost:8080/api/videos`).subscribe(response => this.result = response.text());
  }
}
