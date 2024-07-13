import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AlertTypeEnum } from 'src/app/components/alert/alertType.enum';
import { transactionInterface } from 'src/app/interfaces/transactionInterface';
import { AlertServiceService } from 'src/app/services/alert-service.service';
import { TransactionServiceService } from 'src/app/services/transaction-service.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css'],
})
export class HistoryComponent implements OnInit {
  searchIcon = '../../assets/icons/Search icon.svg';
  alertTypes = AlertTypeEnum;

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
  searchResult$ = new BehaviorSubject<transactionInterface[]>([]);
  tablePageNumber$: number = 0;

  constructor(
    private transactionService: TransactionServiceService,
    private alertService: AlertServiceService
  ) {}

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(next: boolean = false, prev: boolean = false): void {
    if (next) {
      this.tablePageNumber$ += 1;
    } else if (prev && this.tablePageNumber$ > 0) {
      this.tablePageNumber$ -= 1;
    }

    this.transactionService.get(this.tablePageNumber$).subscribe((transaction) => {
      this.info = transaction;
      this.searchResult$.next(this.info);
    });
  }

  getProtocolCodeFromRow(infos: any): void {
    this.protocolCode = infos.protocolNumber;
  }

  onSearchSubmit(name: string): void {
    if (name.trim() !== '') {
      this.transactionService.searchByName(name.trim()).subscribe(
        (result) => {
          this.searchResult$.next(result);
          this.info = result;
        },
        (error) => {
          if (error.status === 404) {
            this.alertService.showAlert(this.alertTypes.error, 'Transação não encontrada. Verifique o texto e tente novamente.');
          } else {
            console.error('Erro ao buscar transação:', error);
            this.alertService.showAlert(this.alertTypes.error, 'Erro ao buscar transação. Tente novamente.');
          }
        }
      );
    } else {
      this.alertService.showAlert(this.alertTypes.error, 'Preencha o campo de texto.');
    }
  }
}
