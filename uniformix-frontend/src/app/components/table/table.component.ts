import { Component, OnInit } from '@angular/core';
import { TableInfoServiceService } from 'src/app/services/tableInfoService.service';
import { tableInfoInterface } from './tableInfoInterface';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {
  info: tableInfoInterface[] = [];
  columns: Array<keyof tableInfoInterface> = ['codigo', 'descricao', 'quantidade', 'categoria', 'fornecedor', 'aquisicao'];

  constructor(private tableService: TableInfoServiceService) {}

  ngOnInit(): void {
    this.tableService.getInfo().subscribe((info) => {
      this.info = info;
    })
  }
}
