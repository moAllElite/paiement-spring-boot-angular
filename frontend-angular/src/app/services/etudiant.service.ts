import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Etudiant} from "../models/etudiant.model";
import {EtatDePaiement, Paiement} from "../models/paiement.interface";

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {
  Host:string='http://localhost:8090'
  constructor(private readonly  http: HttpClient) {}

  recupererListeEtudiants() :Observable<Etudiant[]> {
    return this.http.get<Etudiant[]>(
      `${this.Host}/etudiants?page=0&size=6`
    );
  }

  recupererEtudiantParId(id:number):Observable<Paiement> {
    return this.http.get<Paiement>(
      `${this.Host}/etudiants/${id}/paiements`
    );
  }

  modifierEtudiantParId(id:number):Observable<Etudiant> {
    return  this.http.get<Etudiant>(
      `${this.Host}/etudiants/${id}`
    );
  }

  supprimerEtudiant(id:number):Observable<Etudiant> {
    return this.http.get<Etudiant>(
      `${this.Host}/etudiants/${id}`
    )
  }

  creationEtudiant():Observable<Etudiant>{
    return this.http.get<Etudiant>(
    `${this.Host}/etudiants`
    );
  }


}
