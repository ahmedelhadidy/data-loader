import {Component, Input, OnInit} from '@angular/core';

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

  constructor() { }

  ngOnInit() {

  }




}
