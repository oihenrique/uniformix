import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { tableInfoInterface } from 'src/app/interfaces/tableInfoInterface';
import { transactionInterface } from 'src/app/interfaces/transactionInterface';
import { RouterServiceService } from 'src/app/services/router-service.service';
import { TableInfoServiceService } from 'src/app/services/tableInfoService.service';
import { TransactionServiceService } from 'src/app/services/transaction-service.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  searchIcon = '../../assets/icons/Search icon.svg';

  info: transactionInterface[] = [];
  columns: Array<keyof transactionInterface> = ['protocolNumber', 'uniform', 'employeeName', 'unit', 'quantity', 'operationType', 'users', 'dateTime']

  columnNames: { [key in keyof transactionInterface]: string } = {
    protocolNumber: 'Protocolo',
    uniform: 'Descrição',
    employeeName: 'Colaborador',
    unit: 'Unidade',
    quantity: 'Quant.',
    operationType: 'Tipo',
    users: 'Usuário',
    dateTime: 'Data'
  };

  batchCode: string = "";
  searchResult$: any;
  tablePageNumber$: number = 0;

  constructor(
    private tableService: TableInfoServiceService,
    private routerService: RouterServiceService,
    private transactionService: TransactionServiceService
    ) {}

  ngOnInit(): void {
      this.fetchData();
  }

  fetchData(next: boolean = false, prev: boolean = false): void {
    if (next) {
      this.tablePageNumber$ =+ 1;
    } else if (prev && this.tablePageNumber$ > 0) {
      this.tablePageNumber$ =- 1;
    }

    this.transactionService.get().subscribe((transaction) => {
      console.log(transaction)
      this.info = transaction;
      this.searchResult$ = of(this.info)
    })
  }

  getBatchCodeFromRow(infos: any): void {
    this.batchCode = infos.codigo;
  }


  onSearchSubmit(text: string): void {
    new Observable(item => {
      setTimeout(() => {
        item.next(this.info = this.searchResult$);

      }, 1500);
    }).subscribe()
  }

  async delete(info: tableInfoInterface[], code: string): Promise<void> {
    this.tableService.deleteBatch(info, code).subscribe();
    
    this.routerService.resetPage();
  }
}
