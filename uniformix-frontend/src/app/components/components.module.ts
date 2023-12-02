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

@NgModule({
  declarations: [
    SidebarComponent,
    OrangeButtonComponent,
    GrayButtonComponent,
    SmallButtonComponent,
    TableComponent,
    InputComponent,
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    SidebarComponent,
    OrangeButtonComponent,
    GrayButtonComponent,
    SmallButtonComponent,
    TableComponent,
    InputComponent
  ],
  providers: [TableInfoServiceService],
})
export class ComponentsModule {}
