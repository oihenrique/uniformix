import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent implements OnInit {
  @Input() info: any[] = [];
  @Input() columns: Array<any> = [];
  @Output() onRowClick: EventEmitter<any> = new EventEmitter<any>();

  constructor() {}

  ngOnInit(): void {}

  handleRowClick(infos: any): void {
    this.onRowClick.emit(infos);
  }
}
