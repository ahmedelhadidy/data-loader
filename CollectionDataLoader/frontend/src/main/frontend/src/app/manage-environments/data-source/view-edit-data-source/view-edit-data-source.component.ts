import {Component, Input, OnInit} from '@angular/core';
import {FileuploadService} from "../../../fileupload.service";
import {NotificationService} from "../../../notification.service";

@Component({
  selector: 'app-view-edit-data-source',
  templateUrl: './view-edit-data-source.component.html',
  styleUrls: ['./view-edit-data-source.component.css']
})
export class ViewEditDataSourceComponent implements OnInit {

  @Input()
  database;

  @Input()
  databaseEnv;

  constructor(private fileUploadSer: FileuploadService,private notificationServ: NotificationService) { }

  ngOnInit() {
  }


  addOrUpdate(){
     this.fileUploadSer.createDataBase(this.databaseEnv,this.database).subscribe(database =>{
        this.database = database;
        this.notificationServ.sendNotification(this.database);
     })
  }

}
