import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { transactionInterface } from 'src/app/interfaces/transactionInterface';
import { TransactionServiceService } from 'src/app/services/transaction-service.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css'],
})
export class HistoryComponent implements OnInit {
  searchIcon = '../../assets/icons/Search icon.svg';

  info: transactionInterface[] = [];
  columns: Array<keyof transactionInterface> = [
    'protocolNumber',
    'uniform',
    'employeeName',
    'unit',
    'quantity',
    'operationType',
    'users',
    'dateTime',
  ];

  columnNames: { [key in keyof transactionInterface]: string } = {
    protocolNumber: 'Protocolo',
    uniform: 'Descrição',
    employeeName: 'Colaborador',
    unit: 'Unidade',
    quantity: 'Quant.',
    operationType: 'Tipo',
    users: 'Usuário',
    dateTime: 'Data',
  };

  protocolCode: string = '';
  searchResult$: any;
  tablePageNumber$: number = 0;

  constructor(private transactionService: TransactionServiceService) {}

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(next: boolean = false, prev: boolean = false): void {
    if (next) {
      this.tablePageNumber$ = +1;
    } else if (prev && this.tablePageNumber$ > 0) {
      this.tablePageNumber$ = -1;
    }

    this.transactionService.get().subscribe((transaction) => {
      this.info = transaction;
      this.searchResult$ = of(this.info);
    });
  }

  getProtocolCodeFromRow(infos: any): void {
    this.protocolCode = infos.protocolNumber;
  }

  onSearchSubmit(name: string): void {
    this.transactionService.searchByName(name).subscribe((result) => {
      this.searchResult$ = result;
    });

    new Observable((item) => {
      setTimeout(() => {
        item.next((this.info = this.searchResult$));
      }, 1500);
    }).subscribe();
  }
}
