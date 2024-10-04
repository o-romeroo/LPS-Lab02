import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContentComponent } from './content.component';

const routes: Routes = [
  {
    path: "", component: ContentComponent, children: [
      { path: "automoveis", loadChildren: () => import("../automoveis/automoveis.module").then(module => module.AutomoveisModule) },
      {path: "contratos", loadChildren: () => import("../contratos/contratos.module").then(module => module.ContratosModule) }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContentRoutingModule { }