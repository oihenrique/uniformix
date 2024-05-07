import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ComponentsModule } from '../components/components.module';
import { InventoryComponent } from './inventory/inventory.component';
import { AddBatchComponent } from './inventory/add-batch/add-batch.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BatchServiceService } from '../services/batch-service.service';
import { SupplierServiceService } from '../services/supplier-service.service';
import { CategoryServiceService } from '../services/category-service.service';
import { UniformServiceService } from '../services/uniform-service.service';
import { RouterServiceService } from '../services/router-service.service';
import { AlertServiceService } from '../services/alert-service.service';
import { ConfigurationComponent } from './configuration/configuration.component';


@NgModule({
  declarations: [
    HomeComponent,
    InventoryComponent,
    AddBatchComponent,
    ConfigurationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    ComponentsModule,
    RouterLink
  ],
  exports: [
    HomeComponent,
    InventoryComponent
  ],
  providers: [
    BatchServiceService,
    SupplierServiceService,
    CategoryServiceService,
    UniformServiceService,
    RouterServiceService,
    AlertServiceService
  ]
})
export class PagesModule { }
