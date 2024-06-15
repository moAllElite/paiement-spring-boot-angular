import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {EtudiantService} from "../../services/etudiant.service";
import {map, Observable} from "rxjs";
import {Etudiant} from "../../models/etudiant.model";
import { v4 as uuid } from 'uuid';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";

@Component({
  selector: 'app-load-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements OnInit, AfterViewInit{
  etudiants$!:Observable<Etudiant[]>;
  etudiants:Etudiant[]= [];
  public dataSource:any;
  displayedColumns:string[]= ['id','code',
    'nomComplet', 'photo',
    'dateNaissance','payements'
  ];
  value !:string;
  @ViewChild(MatPaginator) paginator!:MatPaginator;

  @ViewChild(MatSort)sort!:MatSort;

  constructor(private etudiantService:EtudiantService,
              private  router:Router) {
  }


  ngOnInit(): void {
   // this.etudiants=[];
    this.etudiants$= this.etudiantService.recupererListeEtudiants()
    this.etudiants$.subscribe(
      (val)=> {
        console.log(val)
      }
    );


    /* for (let  i = 1 ; i < 100; i++ ){
       this.etudiants.push(
         {
           id: i,
           code: Math.random().toString(20),
           nomComplet: Math.random().toString(20),
           photo:"img.jpg",
           dateNaissance:new  Date()
         });
     }*/

    this.dataSource = new MatTableDataSource(this.etudiants);
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  /**
   * filtrer par etudiants
   * @param event
   */
  filtrerParEtudiant(event: Event) {
    this.value = (event.target as HTMLInputElement).value;
    this.dataSource.filter = this.value;
  }

  protected readonly event = event;
  protected readonly HTMLInputElement = HTMLInputElement;

  /**
   * navigation vers les paiements d'un étudiants
   * @param etudiant
   */
  getPayements(etudiant: Etudiant) {
    this.router.navigateByUrl(`/paiements/:${etudiant.id}`);

  }
}
