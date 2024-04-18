import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {EtudiantService} from "../../services/etudiant.service";
import {async, Observable} from "rxjs";
import {Etudiant} from "../../models/etudiant.model";

@Component({
  selector: 'app-liste-etudiant',
  templateUrl: './liste-etudiant.component.html',
  styleUrl: './liste-etudiant.component.css'
})
export class ListeEtudiantComponent implements OnInit,OnDestroy{
  etudiants$!: Observable<Etudiant[]>;
  constructor(
    route: ActivatedRoute,
    private router: Router,
    private etudiantService: EtudiantService,
  ) {}

  ngOnInit(): void {
    this.etudiants$ = this.etudiantService.recupererListeEtudiants();
  }

  ngOnDestroy(): void {
  }


  protected readonly async = async;
}
