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
import { MatCardModule} from "@angular/material/card";
import {KeycloakService} from "keycloak-angular";
import { StudentsComponent } from './components/students/students.component';
import { MatTableModule} from "@angular/material/table";
import {provideHttpClient, withFetch} from "@angular/common/http";
import { MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import { MatInput} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";
import {provideRouter, RouterModule, withComponentInputBinding, withViewTransitions} from "@angular/router";
import {routes} from './app-routing.module';
import { PayementsDetailsComponent } from './components/payements-details/payements-details.component';
import {MatChip, MatChipOption} from "@angular/material/chips";
function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'scolarite-realm',
        clientId: 'scolarite-app',
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }, bearerPrefix: 'Bearer',
      bearerExcludedUrls: []
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
    StudentsComponent,
    PayementsDetailsComponent,
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
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatInput,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatChip,
    MatChipOption,
  ],
  providers: [
    provideRouter(
      routes,
      withViewTransitions(),
      withComponentInputBinding(),
    ),
    provideAnimationsAsync(),
    provideHttpClient(
      withFetch(),
    ),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    },
    KeycloakService,

  ],
  bootstrap: [AppComponent,]
})
export class AppModule { }
