import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { ComponentsModule } from '../components/components.module';
import { InventoryComponent } from './inventory/inventory.component';


@NgModule({
  declarations: [
    HomeComponent,
    InventoryComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule
  ],
  exports: [
    HomeComponent,
    InventoryComponent
  ]
})
export class PagesModule { }
