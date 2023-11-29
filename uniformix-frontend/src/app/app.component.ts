import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Uniformix';
  addIcon = '../assets/icons/Add icon.svg';
  removeIcon = '../assets/icons/Remove button.svg';
  searchIcon = '../assets/icons/Search icon.svg';
  exportIcon = '../assets/icons/Export icon.svg';
}
