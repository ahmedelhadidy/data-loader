

import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";

import {FileuploadComponent} from "../fileupload/fileupload.component";
import {ManageEnvironmentsComponent} from "../manage-environments/manage-environments.component";

const routs =[
  {path: '' , component: FileuploadComponent },
  {path:'manage-env' , component: ManageEnvironmentsComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routs)],
    exports: [RouterModule]
  }
)

export class appRoutingModule{

}
