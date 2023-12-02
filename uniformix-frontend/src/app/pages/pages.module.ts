import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ComponentsModule } from '../components/components.module';
import { InventoryComponent } from './inventory/inventory.component';
import { AddBatchComponent } from './inventory/add-batch/add-batch.component';


@NgModule({
  declarations: [
    HomeComponent,
    InventoryComponent,
    AddBatchComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule,
    RouterLink
  ],
  exports: [
    HomeComponent,
    InventoryComponent
  ]
})
export class PagesModule { }
