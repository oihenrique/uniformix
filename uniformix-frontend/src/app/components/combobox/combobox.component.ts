import { Component, Input, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-combobox',
  templateUrl: './combobox.component.html',
  styleUrls: ['./combobox.component.css'],
})
export class supplierComboboxComponent implements OnInit {
  @Input() sLabel: string = '';
  @Input() cbList: Array<any> = [];
  list: Array<any> = [];

  constructor() {}

  ngOnInit(): void {
    this.updateList();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['cbList'] && !changes['cbList'].firstChange) {
      this.updateList();
    }
  }

  private updateList(): void {
    if (this.cbList && this.cbList.length > 0) {
      this.list = this.cbList;
    }
  }
}
