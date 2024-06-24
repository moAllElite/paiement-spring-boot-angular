import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EtatDePaiement, Paiement} from "../models/paiement.model";
import {DetailPaiement} from "../models/detail-paiement.model";

@Injectable({
  providedIn: 'root'
})
export class PaiementService {

  Host:string='http://localhost:8090'
  constructor(public   http: HttpClient) {}


  recupererPaiementParCodeEtudiant(code:string):Observable<DetailPaiement> {
    return this.http.get<DetailPaiement>(
      `http://localhost:8090/etudiants/${code}/paiements`
    );
  }


  creationPaiement():Observable<Paiement>{
    return this.http.get<Paiement>(`${this.Host}/etudiants/paiements`);
  }

  modifierEtatDePaiementParId(id:number):Observable<Paiement> {
    return this.http.get<Paiement>(
      `${this.Host}/paiements/updateStatus/${id}`
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
