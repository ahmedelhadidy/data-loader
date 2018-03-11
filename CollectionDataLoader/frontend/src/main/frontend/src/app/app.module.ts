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

@NgModule({
  declarations: [
    AppComponent,
    FileuploadComponent,
    EntityParamComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    AlertModule.forRoot()
  ],
  providers: [FileuploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
