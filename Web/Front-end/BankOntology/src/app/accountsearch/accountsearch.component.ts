import { Component, OnInit, ViewChild } from "@angular/core";
import { NgForm } from "@angular/forms";
import { BankService } from "../bank.service";
import AccountDetails from "../models/accountDetails";

@Component({
  selector: "app-accountsearch",
  templateUrl: "./accountsearch.component.html",
  styleUrls: ["./accountsearch.component.css"]
})
export class AccountsearchComponent implements OnInit {
  @ViewChild("f") signupForm: NgForm;
  id_number: string = "";
  results: AccountDetails[] = [];
  constructor(private bankService: BankService) {}

  ngOnInit() {}

  onSubmit() {
    this.id_number = this.signupForm.value.id_number;
    console.log(this.id_number);
    this.bankService.getByCustomerId(this.id_number).subscribe(res => {
      this.results = res["results"]["bindings"];
    });
    this.signupForm.reset();
  }
}
