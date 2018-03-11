import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Http, Response} from "@angular/http";

@Injectable()
export class FileuploadService {

  constructor(private http: Http) { }


  processFile(file: File): Observable<Response> {

    const formdata: FormData  = new FormData();
    formdata.append('file', file );
    const req = new HttpRequest('POST', '/post' , formdata , {
      reportProgress: true,
      responseType: 'text'
    })
    return this.http.post('/post',formdata);

  }



}
