import {Injectable } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {environment} from "../../environments/environment";

@Injectable()
export class VideoService {

  BACKEND_VIDEO_SERVICE_URL: string = environment.backendUrl + ':' + environment.backendPort + '/api/videos/';

  constructor(private http: HttpClient) {
  }

  public getAll<T>(): Observable<{} | T> {
    return this.http.get<T>(this.BACKEND_VIDEO_SERVICE_URL)
      .pipe(catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }

    // return an observable with a user-facing error message
    this.throwError(
      'Something bad happened; please try again later.');

    return null;
  };

  private throwError(s: string) {
    console.log(s)
  }

}
