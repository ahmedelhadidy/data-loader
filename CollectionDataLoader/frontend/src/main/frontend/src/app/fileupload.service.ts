import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class FileuploadService {

  constructor(private http: HttpClient) { }


  processFile(file: File): Observable<HttpEvent<{}>> {

    formdata: FormData  = new FormData();
    formdata.append('file', file );
    const req = new HttpRequest('POST', '/post' , formdata , {
      reportProgress: true,
      responseType: 'text'
    })
    return this.http.request(req);

  }



}
