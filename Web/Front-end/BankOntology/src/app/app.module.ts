import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { AppComponent } from "./app.component";
import { HomeComponent } from "./home/home.component";
import { AppRouting } from "./app-routing.module";
import { SearchComponent } from "./search/search.component";
import { HttpClientModule } from "@angular/common/http";
import { AccountsearchComponent } from './accountsearch/accountsearch.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, SearchComponent, AccountsearchComponent],
  imports: [BrowserModule, AppRouting, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
