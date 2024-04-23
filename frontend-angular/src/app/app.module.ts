import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AdminTemplateComponent } from './components/admin-template/admin-template.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import { MatBadgeModule} from "@angular/material/badge";
import { MatIconModule} from "@angular/material/icon";
import { MatMenuModule } from "@angular/material/menu";
import {MatDrawerContent, MatSidenavModule} from "@angular/material/sidenav";
import { MatListModule,} from "@angular/material/list";
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PaiementsComponent } from './components/paiements/paiements.component';
import { CardStudentComponent } from './components/card-student/card-student.component';
import { ListStudentComponent } from './components/list-student/list-student.component';
import { MatCardModule} from "@angular/material/card";
import {KeycloakService} from "keycloak-angular";

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'scolarite-realm',
        clientId: 'Scolarite App',
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}
@NgModule({
  declarations: [
    AppComponent,
    AdminTemplateComponent,
    HomeComponent,
    ProfileComponent,
    DashboardComponent,
    PaiementsComponent,
    CardStudentComponent,
    ListStudentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatBadgeModule,
    MatIconModule,
    MatMenuModule,
    MatDrawerContent,
    MatSidenavModule,
    MatListModule,
    MatCardModule,

  ],
  providers: [
    provideAnimationsAsync(),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    },
    KeycloakService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
