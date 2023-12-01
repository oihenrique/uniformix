import { Component, OnInit } from '@angular/core';

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
  
  constructor() { }

  ngOnInit(): void {
  }

}
