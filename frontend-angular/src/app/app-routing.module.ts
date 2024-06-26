import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {AuthGuard} from "./guards/auth.guard";
import {StudentsComponent} from "./components/students/students.component";
import {PaiementsComponent} from "./components/paiements/paiements.component";
import {PayementsDetailsComponent} from "./components/payements-details/payements-details.component";

export const routes: Routes = [
  {path:'',redirectTo:'home',pathMatch:'full'},
  {path:'home',component:HomeComponent},
  {path:'profile',component:ProfileComponent},
  {
    path:'dashboard',
    data:{roles:['ROLE_ADMIN'] },
    canActivate:[AuthGuard],
    component:DashboardComponent,
  },
  {
    path:'students',
    data:{roles:['ROLE_ADMIN','ADMIN'] },
    canActivate:[AuthGuard],
    children:[
      {
        path:'load',component:StudentsComponent,
      },

    ]
  },
  {
    path:'payments',
    data:{roles:['ROLE_ADMIN'] },
    canActivate:[AuthGuard],
    children:[
      {
        path:'load',
        component:PaiementsComponent,
      },
      {
        path:'etudiant/code/:codeEtudiant',
        component: PayementsDetailsComponent,
      }
    ],
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule,]
})
export class AppRoutingModule { }
