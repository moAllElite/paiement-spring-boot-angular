import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Etudiant} from "../models/etudiant.model";
import {EtatDePaiement, Paiement} from "../models/paiement.model";
import {EtudiantRoot} from "../models/etudiant-root.model";

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {
  Host:string='http://localhost:8090'
  constructor(public  http: HttpClient) {}

  recupererListeEtudiants() :Observable<EtudiantRoot> {
    return this.http.get<EtudiantRoot>('http://localhost:8090/etudiants');
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
