import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Http, RequestOptions, Response, Headers} from "@angular/http";

@Injectable()
export class FileuploadService {

  constructor(private http: Http) { }


  getEntity(file: File): Observable<Response> {

    const formdata: FormData  = new FormData();
    formdata.append('file', file );

    return this.http.post('/post',formdata);

  }

  processEntity(entity): Observable<Response> {

    let bodyString = JSON.stringify(entity); // Stringify payload
    let headers = new Headers ({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    let options = new RequestOptions({ headers: headers });
    return this.http.post('/process',bodyString, options);
  }


}
