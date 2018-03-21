import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {FileuploadService} from '../fileupload.service';
import {NotificationService} from "../notification.service";
import {Subscription} from "rxjs/Subscription";
import 'rxjs/Rx';

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
   notificationMessage: string;
   entity: any;
   env : any[];
   selectedEnv ;
   subscription: Subscription;

   @ViewChild('fileComp') fileComp;



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

    this.fileuploadServ.getEnvironments().subscribe(envs =>{
      this.env = envs;
      console.log(this.env);
    })
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

      console.log('error happened');
      console.log(error);
      const filename = this.fileComp.nativeElement.value;
      this.fileComp.nativeElement.value = '';
      this.errorMessage ='Failed to upload file ' + filename+' :-'+error['_body'];
    });
    this.selectedFiles = undefined;
  }

  processEntity(){

    this.fileuploadServ.processEntity(this.entity,this.selectedEnv).subscribe(response =>{
      console.log('Entity processed successfully')
      this.notificationMessage='Entity '+this.entity.name+' processed successfully '
    },
    error => {
      console.log('server error '+error);
      this.errorMessage = 'Error processing Entity '+this.entity.name+' :-'+ error['_body'];
    }
      );

  }

  dismessError() {
    this.errorMessage = undefined;
  }

  dismessNotification() {
    this.notificationMessage  = undefined;
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

}
