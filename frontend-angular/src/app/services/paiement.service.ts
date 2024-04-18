import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EtatDePaiement, Paiement} from "../models/paiement.interface";

@Injectable({
  providedIn: 'root'
})
export class PaiementService {

  Host:string='http://localhost:8090'
  constructor(private readonly  http: HttpClient) {}


  recupererPaiementParCodeEtudiant(code:string):Observable<Paiement> {
    return this.http.get<Paiement>(
      `${this.Host}etudiants/${code}/paiements`
    );
  }


  creationPaiement():Observable<Paiement>{
    return this.http.get<Paiement>(`${this.Host}/etudiants/paiements`);
  }

  modifierEtatDePaiementParId(id:number):Observable<Paiement> {
    return this.http.get<Paiement>(
      `${this.Host}/paiements/updateStatus//${id}`
    );
  }

  recupererPaiementParEtat(etat:EtatDePaiement):Observable<Paiement> {
    return this.http.get<Paiement>(`${this.Host}/paiements/search?etat=${etat}`);
  }

  recupererListePaiement():Observable<Paiement[]> {
    return this.http.get<Paiement[]>(
      `${this.Host}/paiements`
    );
  }

  consulterFichierPDFParIdPaiement(idPaiement:number):Observable<Paiement> {
    return this.http.get<Paiement>(
      `${this.Host}/paiements/fichier/${idPaiement}`
    );
  }
}
