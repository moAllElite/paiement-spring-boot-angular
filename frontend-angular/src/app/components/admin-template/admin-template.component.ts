import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-admin-template',
  templateUrl: './admin-template.component.html',
  styleUrl: './admin-template.component.css'
})
export class AdminTemplateComponent implements  OnInit{
  profile!:any;
  constructor(private keycloakService: KeycloakService) {

  }
  ngOnInit(): void {
    let isAutehenticated = this.keycloakService.isLoggedIn();
    if (isAutehenticated) {
      this.keycloakService.loadUserProfile().then(
        value => this.profile = value
      );
    }else {
      console.log("User profile not authenticated");
    }

  }

  onLogin(){
    this.keycloakService.login();
  }

}
