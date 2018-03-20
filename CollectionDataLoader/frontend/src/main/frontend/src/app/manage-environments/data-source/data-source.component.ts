import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NotificationService} from "../../notification.service";
import {Subscription} from "rxjs/Subscription";
import {isNullOrUndefined} from "util";

@Component({
  selector: 'app-data-source',
  templateUrl: './data-source.component.html',
  styleUrls: ['./data-source.component.css'],
  providers: [NotificationService]
})
export class DataSourceComponent implements OnInit,OnDestroy {


  @Input()
   selectedEnv;

  selectedDataSource

  subscription: Subscription;

  newDatabase;


  constructor(private notificationServ: NotificationService) { }

  ngOnInit() {

    this.subscription= this.notificationServ.getNotifications().subscribe(database =>{
      this.selectedDataSource = database;
      //this.selectedEnv.database.push(database);

      let indx = this.selectedEnv.database.indexOf(this.newDatabase);
      if(indx>-1){
        this.selectedEnv.database[indx] = database;
      }
      this.newDatabase = undefined;

    });

  }

  addNew(){
    this.newDatabase = {
      databaseName: "New Database",
      connectionString: "",
      username: "",
      password: "",
      maxConnections: "",
      initialDeff: false
    }
    this.selectedEnv.database.push(this.newDatabase);
  }

  ngOnDestroy(){
    this.subscription .unsubscribe();
  }

}
