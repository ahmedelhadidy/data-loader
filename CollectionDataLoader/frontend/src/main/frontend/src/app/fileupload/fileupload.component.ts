import { Component, OnInit } from '@angular/core';
import {FileuploadService} from '../fileupload.service';
import {HttpErrorResponse, HttpEvent, HttpEventType, HttpResponse} from '@angular/common/http';

@Component({

  selector: 'app-fileupload',
  templateUrl: './fileupload.component.html',
  styleUrls: ['./fileupload.component.css']
})
export class FileuploadComponent implements OnInit {

   selectedFiles: FileList;
   currentFile: File;
   currentFileUploadProgress: {percentage: number} = {percentage: 0};
   errorMessage: string;
   entity: any;



  constructor(private fileuploadServ: FileuploadService) { }

  ngOnInit() {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.currentFile = this.selectedFiles.item(0);
    this.fileuploadServ.processFile(this.currentFile).subscribe(response => {
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

  dismessError() {
    this.errorMessage = undefined;
  }

}
