import {Etudiant} from "./etudiant.model";

export interface EtudiantRoot {
  _embedded: Embedded
  page: Page
}

export interface Embedded {
  etudiants: Etudiant[]
}

export interface Page {
  size: number
  totalElements: number
  totalPages: number
  number: number
}
