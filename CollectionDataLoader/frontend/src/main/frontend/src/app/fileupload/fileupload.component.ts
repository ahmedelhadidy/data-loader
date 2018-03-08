import { Component, OnInit } from '@angular/core';
import {FileuploadService} from '../fileupload.service';
import {HttpEvent, HttpEventType, HttpResponse} from '@angular/common/http';

@Component({

  selector: 'app-fileupload',
  templateUrl: './fileupload.component.html',
  styleUrls: ['./fileupload.component.css']
})
export class FileuploadComponent implements OnInit {

   selectedFiles: FileList;
   currentFile: File;
   currentFileUploadProgress: {percentage: number} = {percentage: 0};




  constructor(private fileuploadServ: FileuploadService) { }

  ngOnInit() {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.currentFile = this.selectedFiles.item(0);
    this.fileuploadServ.processFile(this.currentFile).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.currentFileUploadProgress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });
    this.selectedFiles = undefined;
  }



}
