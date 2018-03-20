import { Component, OnInit } from '@angular/core';
import {FileuploadService} from "../fileupload.service";

@Component({
  selector: 'app-manage-environments',
  templateUrl: './manage-environments.component.html',
  styleUrls: ['./manage-environments.component.css']
})
export class ManageEnvironmentsComponent implements OnInit {

  allEnvs: any [];

  selectedEnv;

  constructor(private fileuploadServ: FileuploadService) { }

  ngOnInit() {
    this.fileuploadServ.getEnvironments().subscribe(envs =>{
      this.allEnvs = envs;
      console.log(this.allEnvs);
    })
  }

  createNew(){
    let newEnv = {
      name:"",
      new:true
    }
    this.allEnvs.push(newEnv);
  }

  save(env){
      this.fileuploadServ.createEnv(env).subscribe(savedEnv =>{
        this.selectedEnv = undefined;
        savedEnv.new=false;
        for(let e of this.allEnvs){
          if(e.name === savedEnv.name){
            let index = this.allEnvs.indexOf(e);
            this.allEnvs[index] = savedEnv;
          }
        }
      })
  }


}
