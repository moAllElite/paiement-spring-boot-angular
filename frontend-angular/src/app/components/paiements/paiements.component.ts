import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Paiement} from "../../models/paiement.model";
import {PaiementService} from "../../services/paiement.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-paiements',
  templateUrl: './paiements.component.html',
  styleUrl: './paiements.component.css'
})
export class PaiementsComponent implements OnInit,AfterViewInit{
  public payements: Array<Paiement> = [];
  displayedColumns: Iterable<string> = [
    'id', 'date','montant','type','etat','recu','etudiant'
  ];

  constructor(private paiementService: PaiementService) {}

  @ViewChild(MatSort) sortingPayement!: MatSort;
  @ViewChild(MatPaginator) paginator!:MatPaginator | null;
  public dataSource!: MatTableDataSource<Paiement>;

  /**
   * On récuperer la liste des paiements et on fait la pagination et le trie
   */
  ngOnInit() {
    this.paiementService.recupererListePaiement()
      .subscribe(
        {
          next :  (value) => {
            this.payements = value;
            this.dataSource = new MatTableDataSource(this.payements);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sortingPayement;
          },
          error:(err) => console.log(err)
        },
      );
  }
  ngAfterViewInit(): void {
  }

}
