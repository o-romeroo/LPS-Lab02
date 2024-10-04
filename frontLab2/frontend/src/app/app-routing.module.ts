import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: "", redirectTo: "content/automoveis", pathMatch: "full" },
  { path: "login", loadChildren: () => import('./modules/login/login.module').then(module => module.LoginModule) },
  { path: "content", loadChildren: () => import('./modules/content/content.module').then(module => module.ContentModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
