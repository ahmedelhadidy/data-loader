import { Injectable } from '@angular/core';
import {Subject} from "rxjs/Subject";

@Injectable()
export class NotificationService {

  subject = new Subject<any>() ;

  constructor() {


  }

  sendNotification(noti: any){
    this.subject.next(noti);
  }

  clear(){
    this.subject.next();
  }

  getNotifications(){
    return this.subject.asObservable();
  }




}
