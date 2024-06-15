import {Component, input, InputSignal, OnInit, output} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";
import {Router} from "@angular/router";
@Component({
  selector: 'app-admin-template',
  templateUrl: './admin-template.component.html',
  styleUrl: './admin-template.component.css'
})
export class AdminTemplateComponent implements  OnInit{
  profile!:KeycloakProfile ;
  isAutehenticated!: boolean;
  username=output<string | undefined>();
  constructor(private keycloakService: KeycloakService) {}
  ngOnInit(): void {
    this.isAutehenticated= this.keycloakService.isLoggedIn();
    if (this.isAutehenticated) {
      this.keycloakService.loadUserProfile().then(
        value => {
          this.profile = value;
          this.username.emit(value.username);
        }
      );
      console.log("is authenticated")
    }else {
      console.log("User profile not authenticated");
    }

  }

  onLogin():void{
    this.keycloakService.login();
  }
  onLogout():void{
    this.keycloakService.logout(window.location.origin).then();
  }
}
