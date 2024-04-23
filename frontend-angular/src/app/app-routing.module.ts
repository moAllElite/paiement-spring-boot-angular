import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {PaiementsComponent} from "./components/paiements/paiements.component";
import {ListStudentComponent} from "./components/list-student/list-student.component";

const routes: Routes = [
  {path:'home',component:HomeComponent},
  {path:'profile',component:ProfileComponent},
  {path:'dashboard',component:DashboardComponent},
  {path:'students',component:ListStudentComponent},
  {path:'payments',component:PaiementsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
