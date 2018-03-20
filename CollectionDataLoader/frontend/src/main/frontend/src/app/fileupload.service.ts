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

  processEntity(entity,env): Observable<Response> {

    const request = {
      entity: entity,
      env: env
    }

    let bodyString = JSON.stringify(request); // Stringify payload
    let headers = new Headers ({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    let options = new RequestOptions({ headers: headers });
    return this.http.post('/process',bodyString, options);
  }

  getEnvironments() : Observable<any>{

    let headers = new Headers ({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    let options = new RequestOptions({ headers: headers });
    return this.http.get('/getenvs',options).map(response =>{
      return response.json();
    });
  }

  createEnv(env) : Observable<any> {
    let headers = new Headers ({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    let options = new RequestOptions({ headers: headers });
    return this.http.post('/createenv',JSON.stringify(env),options).map(response =>{
      return response.json();
    });
  }

  createDataBase(env , database) : Observable<any> {

   let operationEnv = Object.assign({},env);

    let headers = new Headers ({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
    let options = new RequestOptions({ headers: headers });
    operationEnv.database = [];
    operationEnv.database.push(database);

    return this.http.post('/creatdb',JSON.stringify(operationEnv),options).map(response =>{
      return response.json();
    });
  }




}
