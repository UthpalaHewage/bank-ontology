import { Routes, RouterModule } from "@angular/router";
import { Component, NgModule } from "@angular/core";

import { HomeComponent } from "./home/home.component";
import { SearchComponent } from "./search/search.component";
import { AccountsearchComponent } from "./accountsearch/accountsearch.component";

const routes: Routes = [
  { path: "home", component: HomeComponent },
  { path: "search", component: SearchComponent },
  { path: "accountsearch", component: AccountsearchComponent },
  { path: "", redirectTo: "/home", pathMatch: "full" }
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)],
  declarations: []
})
export class AppRouting {}
