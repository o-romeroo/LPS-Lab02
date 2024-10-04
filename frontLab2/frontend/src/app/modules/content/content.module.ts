import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContentComponent } from './content.component';
import { MenuBarComponent } from './menu-bar/menu-bar.component';
import { MaterialModule } from 'src/app/material-module';
import { MenuLateralComponent } from './menu-lateral/menu-lateral.component';
import { ContentRoutingModule } from './content-routing.module';



@NgModule({
  declarations: [
    ContentComponent,
    MenuBarComponent,
    MenuLateralComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ContentRoutingModule,
  ]
})
export class ContentModule { }
