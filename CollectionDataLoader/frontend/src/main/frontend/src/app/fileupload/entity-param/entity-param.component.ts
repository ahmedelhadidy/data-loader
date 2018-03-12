import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NotificationService} from "../../notification.service";

@Component({
  selector: 'app-entity-param',
  templateUrl: './entity-param.component.html',
  styleUrls: ['./entity-param.component.css']
})
export class EntityParamComponent implements OnInit {
  @Input()
  entityParam:  {
    "name": string,
    "value": string,
    "dataType": string,
    "dateFormate": string
  };

  constructor(private notificationService: NotificationService) { }

  ngOnInit() {

  }

  onParamValueChange(){
    this.notificationService.sendNotification(this.entityParam);
  }




}
