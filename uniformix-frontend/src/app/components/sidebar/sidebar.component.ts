import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  activeButton: string = '';

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.router.events.pipe(
      filter(() => this.router.url.startsWith(''))
    ).subscribe(() => {
      this.markAsActive('');
    });

    this.router.events.pipe(
      filter(() => this.router.url.startsWith('/inventory'))
    ).subscribe(() => {
      this.markAsActive('Estoque');
    });

    this.router.events.pipe(
      filter(() => this.router.url.startsWith('/suppliers'))
    ).subscribe(() => {
      this.markAsActive('Fornecedores');
    });

    this.router.events.pipe(
      filter(() => this.router.url.startsWith('/categories'))
    ).subscribe(() => {
      this.markAsActive('Categorias');
    });

    this.router.events.pipe(
      filter(() => this.router.url.startsWith('/user'))
    ).subscribe(() => {
      this.markAsActive('Usuário');
    });

    this.router.events.pipe(
      filter(() => this.router.url.startsWith('/settings'))
    ).subscribe(() => {
      this.markAsActive('Configurações');
    });
  }

  markAsActive(buttonName: string): void {
    this.activeButton = buttonName;
  }
}
