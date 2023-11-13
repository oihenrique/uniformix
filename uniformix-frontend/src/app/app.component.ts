import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'uniformix';
  addButtonText = 'adicionar';
  removeButtonText = 'remover';
  searchButtonText = 'pesquisar';
  addIcon = '../assets/icons/Add icon.svg';
  removeIcon = '../assets/icons/Remove button.svg';
}
