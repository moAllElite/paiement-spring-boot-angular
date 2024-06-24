import {Etudiant} from "./etudiant.model";

export type DetailPaiement = InfosPaiement[]

export interface InfosPaiement {
  id: number
  date: string
  montant: number
  type: string
  etat: any
  recu: string
  etudiant: Etudiant
}
