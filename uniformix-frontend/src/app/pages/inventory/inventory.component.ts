import { Component, OnInit } from '@angular/core';
import { tableInfoInterface } from 'src/app/interfaces/tableInfoInterface';
import { BatchServiceService } from 'src/app/services/batch-service.service';
import { RouterServiceService } from 'src/app/services/router-service.service';
import { TableInfoServiceService } from 'src/app/services/tableInfoService.service';
import { Observable, of } from 'rxjs'

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {
  addIcon = '../../assets/icons/Add icon.svg';
  removeIcon = '../../assets/icons/Remove button.svg';
  searchIcon = '../../assets/icons/Search icon.svg';
  exportIcon = '../../assets/icons/Export icon.svg';

  info: tableInfoInterface[] = [];
  columns: Array<keyof tableInfoInterface> = ['codigo', 'descricao', 'quantidade', 'categoria', 'fornecedor', 'aquisicao']
  batchCode: string = "";
  searchResult$: any;
  tablePageNumber$: number = 0;
  totalInventory: number = 0;

  constructor(
    private tableService: TableInfoServiceService,
    private routerService: RouterServiceService,
    private batchService: BatchServiceService
    ) {}

  ngOnInit(): void {
      this.fetchData();
      this.batchService.getTotalInventory().subscribe((number) => {
        this.totalInventory = number;
      })
  }

  fetchData(next: boolean = false, prev: boolean = false): void {
    if (next) {
      this.tablePageNumber$ =+ 1;
    } else if (prev && this.tablePageNumber$ > 0) {
      this.tablePageNumber$ =- 1;
    }

    this.tableService.getInfo(this.tablePageNumber$).subscribe((info) => {
      this.info = info;
      this.searchResult$ = of(this.info);
    });
  }

  getBatchCodeFromRow(infos: any): void {
    this.batchCode = infos.codigo;
  }

  onSearchSubmit(text: string): void {
    this.batchService.getBatchByText(text).subscribe((result) => {
      this.searchResult$ = result;
    })

    new Observable(item => {
      setTimeout(() => {
        item.next(this.info = this.searchResult$);

      }, 1500);
    }).subscribe()
  }

  delete(info: tableInfoInterface[], code: string): void {
    this.tableService.deleteBatch(info, code).subscribe();
    setTimeout(() => {
      this.routerService.resetPage();
    }, 1500)
  }
}
