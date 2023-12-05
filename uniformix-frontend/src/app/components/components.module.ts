import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SidebarComponent } from './sidebar/sidebar.component';
import { OrangeButtonComponent } from './orangeButton/orangeButton.component';
import { GrayButtonComponent } from './grayButton/grayButton.component';
import { SmallButtonComponent } from './small-button/small-button.component';
import { TableComponent } from './table/table.component';
import { TableInfoServiceService } from '../services/tableInfoService.service';
import { InputComponent } from './input/input.component';
import { supplierComboboxComponent } from './combobox/combobox.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    SidebarComponent,
    OrangeButtonComponent,
    GrayButtonComponent,
    SmallButtonComponent,
    TableComponent,
    InputComponent,
    supplierComboboxComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  exports: [
    SidebarComponent,
    OrangeButtonComponent,
    GrayButtonComponent,
    SmallButtonComponent,
    TableComponent,
    InputComponent,
    supplierComboboxComponent
  ],
  providers: [
    TableInfoServiceService
  ],
})
export class ComponentsModule {}
