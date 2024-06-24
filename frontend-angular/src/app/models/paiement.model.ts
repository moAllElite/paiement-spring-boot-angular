import {Etudiant} from "./etudiant.model";

export interface Paiement {
  id: number
  date: string
  montant: number
  type: string
  etat: any
  recu: string
  etudiant: Etudiant
}
export enum TypeDePaiement{
  CASH="CASH",
  CHEQUE="CHEQUE",
  TRANSFERT="TRANSFERT",
  VERSEMENT="VERSEMENT"
}
export enum EtatDePaiement{
  CREE="CREE",
  VALIDE="VALIDE",
  REJETE="REJETE"
}
