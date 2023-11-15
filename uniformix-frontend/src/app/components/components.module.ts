import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import { OrangeButtonComponent } from './orangeButton/orangeButton.component';
import { GrayButtonComponent } from './grayButton/grayButton.component';
import { SmallButtonComponent } from './small-button/small-button.component';
import { TableComponent } from './table/table.component';
import { TableInfoServiceService } from '../services/table-info-service.service';

@NgModule({
  declarations: [
    SidebarComponent,
    OrangeButtonComponent,
    GrayButtonComponent,
    SmallButtonComponent,
    TableComponent,
  ],
  imports: [CommonModule],
  exports: [
    SidebarComponent,
    OrangeButtonComponent,
    GrayButtonComponent,
    SmallButtonComponent,
    TableComponent,
  ],
  providers: [TableInfoServiceService],
})
export class ComponentsModule {}
