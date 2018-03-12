import {Component, OnDestroy, OnInit} from '@angular/core';
import {FileuploadService} from '../fileupload.service';
import {HttpErrorResponse, HttpEvent, HttpEventType, HttpResponse} from '@angular/common/http';
import {NotificationService} from "../notification.service";
import {Subscription} from "rxjs/Subscription";

@Component({

  selector: 'app-fileupload',
  templateUrl: './fileupload.component.html',
  styleUrls: ['./fileupload.component.css'],
  providers:[NotificationService]
})
export class FileuploadComponent implements OnInit, OnDestroy {

   selectedFiles: FileList;
   currentFile: File;
   currentFileUploadProgress: {percentage: number} = {percentage: 0};
   errorMessage: string;
   entity: any;
   subscription: Subscription;



  constructor(private fileuploadServ: FileuploadService, private notificationService: NotificationService  ) { }

  ngOnInit() {
    this.subscription= this.notificationService.getNotifications().map(data =>{
       return data as {
         "name": string,
         "value": string,
         "dataType": string,
         "dateFormate": string
       };
    }).subscribe(notification =>{
        if(this.entity){
          for (let p of this.entity['parameters']['param']){
            if( notification.name === p['name'] ){
              p['value'] = notification.value;
            }
          }
        }
     });
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.currentFile = this.selectedFiles.item(0);
    this.fileuploadServ.getEntity(this.currentFile).subscribe(response => {
          console.log('File is completely uploaded!');
          this.entity = response.json();
          console.log(this.entity);
    } , error => {
      let er = error as HttpErrorResponse;
      console.log('error happened');
      console.log(er.error);
      this.errorMessage = er.error;
    });
    this.selectedFiles = undefined;
  }

  processEntity(){

    this.fileuploadServ.processEntity(this.entity).subscribe(response =>{
      console.log('Entity processed successfully')
    },
    error => {
      console.log('server error '+error);
    }
      );

  }

  dismessError() {
    this.errorMessage = undefined;
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

}
