import { Component, OnInit } from '@angular/core';
import { tableInfoInterface } from 'src/app/interfaces/tableInfoInterface';
import { RouterServiceService } from 'src/app/services/router-service.service';
import { TableInfoServiceService } from 'src/app/services/tableInfoService.service';

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

  constructor(
    private tableService: TableInfoServiceService,
    private routerService: RouterServiceService
    ) {}

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(): void {
    this.tableService.getInfo().subscribe((info) => {
      this.info = info;
    });
  }

  getBatchCodeFromRow(infos: any): void {
    this.batchCode = infos.codigo;
  }

  delete(info: tableInfoInterface[], code: string) {
    this.tableService.deleteBatch(info, code).subscribe();
    setTimeout(() => {
      this.routerService.resetPage();
    }, 1500)
  }
}
