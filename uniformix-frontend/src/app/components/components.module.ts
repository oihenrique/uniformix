import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import { OrangeButtonComponent } from './orangeButton/orangeButton.component';
import { GrayButtonComponent } from './grayButton/grayButton.component';

@NgModule({
  declarations: [SidebarComponent, OrangeButtonComponent, GrayButtonComponent],
  imports: [CommonModule],
  exports: [SidebarComponent, OrangeButtonComponent, GrayButtonComponent],
})
export class ComponentsModule {}
