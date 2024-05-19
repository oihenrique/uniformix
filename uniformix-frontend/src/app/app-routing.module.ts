import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { InventoryComponent } from './pages/inventory/inventory.component';
import { AddBatchComponent } from './pages/inventory/add-batch/add-batch.component';
import { ConfigurationComponent } from './pages/configuration/configuration.component';
import { AddUnitComponent } from './pages/configuration/add-unit/add-unit.component';
import { AddCategoryComponent } from './pages/configuration/add-category/add-category.component';
import { AddSupplierComponent } from './pages/configuration/add-supplier/add-supplier.component';
import { TransactionComponent } from './pages/transaction/transaction.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'inventory', component: InventoryComponent},
  {path: 'inventory/add', component: AddBatchComponent},
  {path: 'transaction', component: TransactionComponent},
  {path: 'settings', component: ConfigurationComponent},
  {path: 'settings/unit', component: AddUnitComponent},
  {path: 'settings/category', component: AddCategoryComponent},
  {path: 'settings/supplier', component: AddSupplierComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
