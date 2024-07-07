import { Component, OnInit } from '@angular/core';
import { tableInfoInterface } from 'src/app/interfaces/tableInfoInterface';
import { BatchServiceService } from 'src/app/services/batch-service.service';
import { RouterServiceService } from 'src/app/services/router-service.service';
import { TableInfoServiceService } from 'src/app/services/tableInfoService.service';
import { BehaviorSubject, catchError, of } from 'rxjs';
import { AlertServiceService } from 'src/app/services/alert-service.service';
import { AlertTypeEnum } from 'src/app/components/alert/alertType.enum';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css'],
})
export class InventoryComponent implements OnInit {
  addIcon = '../../assets/icons/Add icon.svg';
  transactionIcon = '../../assets/icons/transaction.svg';
  searchIcon = '../../assets/icons/Search icon.svg';
  exportIcon = '../../assets/icons/Export icon.svg';
  alertTypes = AlertTypeEnum;

  info: tableInfoInterface[] = [];
  columns: Array<keyof tableInfoInterface> = [
    'codigo',
    'descricao',
    'quantidade',
    'categoria',
    'fornecedor',
    'aquisicao',
  ];

  columnNames: { [key in keyof tableInfoInterface]: string } = {
    codigo: 'Cód.',
    descricao: 'Descrição',
    categoria: 'Categoria',
    fornecedor: 'Fornecedor',
    quantidade: 'Quant.',
    aquisicao: 'Data',
  };

  batchCode: string = '';
  searchResult$ = new BehaviorSubject<tableInfoInterface[]>([]);
  tablePageNumber$: number = 0;
  totalInventory: number = 0;

  constructor(
    private tableService: TableInfoServiceService,
    private routerService: RouterServiceService,
    private batchService: BatchServiceService,
    private alertService: AlertServiceService
  ) {}

  ngOnInit(): void {
    this.fetchData();
    this.batchService.getTotalInventory().subscribe((number) => {
      this.totalInventory = number;
    });
  }

  fetchData(next: boolean = false, prev: boolean = false): void {
    if (next) {
      this.tablePageNumber$ += 1;
    } else if (prev && this.tablePageNumber$ > 0) {
      this.tablePageNumber$ -= 1;
    }

    this.tableService.getInfo(this.tablePageNumber$).subscribe((info) => {
      this.info = info;
      this.searchResult$.next(this.info);
    });
  }

  getBatchCodeFromRow(infos: any): void {
    this.batchCode = infos.codigo;
  }

  exportBatchInfo() {
    this.batchService.downloadBatchReport();
  }

  onSearchSubmit(text: string): void {
    if (text.trim() !== '') {
      this.batchService.getBatchByText(text.trim()).subscribe(
        (result) => {
          this.searchResult$.next(result);
          this.info = result;
        }, 
        (error) => {
          if (error.status === 404) {
            this.alertService.showAlert(this.alertTypes.error, 'Lote não encontrado. Verifique o texto e tente novamente.');
          } else {
            console.error('Erro ao buscar lote:', error);
            this.alertService.showAlert(this.alertTypes.error, 'Erro ao buscar lote. Tente novamente.');
          }
        }
      );
    } else {
      this.alertService.showAlert(this.alertTypes.error, 'Preencha o campo de texto.');
    }
  }
  
  

  async delete(info: tableInfoInterface[], code: string): Promise<void> {
    this.tableService.deleteBatch(info, code).subscribe();

    this.routerService.resetPage();
  }
}
