import {AfterViewInit, Component, OnInit, signal, ViewChild, WritableSignal} from '@angular/core';
import {EtudiantService} from "../../services/etudiant.service";
import {map, Observable} from "rxjs";
import {Etudiant} from "../../models/etudiant.model";
import { v4 as uuid } from 'uuid';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";
import {EtudiantRoot} from "../../models/etudiant-root.model";

@Component({
  selector: 'app-load-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements OnInit, AfterViewInit{
  public etudiants$!:Observable<Etudiant[]>;
  public etudiants:WritableSignal<Etudiant[]>=signal([]);
  public dataSource!:MatTableDataSource<Etudiant>;
  public displayedColumns:string[]= ['code',
    'nomComplet', 'photo',
    'dateNaissance','payements'
  ];
  value !:string;

  @ViewChild(MatPaginator) paginator!:MatPaginator;

  @ViewChild(MatSort)sort!:MatSort;

  constructor(private etudiantService:EtudiantService,
              private  router:Router) {}

  /**
   *  au chargement la page on affiche la liste des étudiants
   */

  ngOnInit(): void {
    this.etudiantService.recupererListeEtudiants().subscribe(
      {
        next :  (value1) => {
          this.etudiants.set(value1._embedded.etudiants);
          this.dataSource = new MatTableDataSource(this.etudiants());
        }
      }
    );
  }

  ngAfterViewInit(): void {
    this.etudiantService.recupererListeEtudiants().subscribe(
      {
        next :  (value1) => {
          this.etudiants.set(value1._embedded.etudiants);
          this.dataSource = new MatTableDataSource(this.etudiants());
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        }
      });
  }

  /**
   * filtrer par etudiants
   * @param event
   */
  filtrerParEtudiant(event: Event) {
    this.value = (event.target as HTMLInputElement).value;
    this.dataSource.filter = this.value;
  }


  /**
   * navigation vers les paiements d'un étudiants
   * @param etudiant
   */
  getPayements(etudiant: Etudiant) {
    this.router.navigateByUrl(`/payments/etudiant/code/${etudiant.code}`).then();

  }
}
