import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaterialModule } from './material-module';
import { ContentModule } from './modules/content/content.module';
import { FormatoMonetarioDirective } from './directives/money-format.directive';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AutomoveisModule } from './modules/automoveis/automoveis.module';


@NgModule({
  declarations: [
    AppComponent,
    FormatoMonetarioDirective,
  ],
  imports: [
    AppRoutingModule,
    BrowserAnimationsModule,
    AutomoveisModule,
    MaterialModule,
    ContentModule,
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }


