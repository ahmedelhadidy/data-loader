import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FileuploadComponent } from './fileupload/fileupload.component';
import {FileuploadService} from "./fileupload.service";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { EntityParamComponent } from './fileupload/entity-param/entity-param.component';
import { AlertModule } from 'ngx-bootstrap';

import {appRoutingModule} from "./routs/app.routing";
import { DataSourceComponent } from './manage-environments/data-source/data-source.component';
import { ManageEnvironmentsComponent } from './manage-environments/manage-environments.component';
import { ViewEditDataSourceComponent } from './manage-environments/data-source/view-edit-data-source/view-edit-data-source.component';

@NgModule({
  declarations: [
    AppComponent,
    FileuploadComponent,
    EntityParamComponent,
    ManageEnvironmentsComponent,
    DataSourceComponent,
    ManageEnvironmentsComponent,
    ViewEditDataSourceComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    appRoutingModule
  ],
  providers: [FileuploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
