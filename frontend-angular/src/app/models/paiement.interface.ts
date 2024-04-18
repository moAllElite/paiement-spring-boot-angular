export interface Paiement {
  date:Date ,
  montant: number,
  type: TypeDePaiement,
  etat:EtatDePaiement,
  recu: string
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
