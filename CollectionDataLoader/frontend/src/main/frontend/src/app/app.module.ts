import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FileuploadComponent } from './fileupload/fileupload.component';
import {FileuploadService} from "./fileupload.service";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    FileuploadComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule
  ],
  providers: [FileuploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
