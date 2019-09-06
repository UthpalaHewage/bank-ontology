import { Component, OnInit, ViewChild } from "@angular/core";
import { NgForm } from "@angular/forms";
import { BankService } from "../bank.service";
import UserDetails from "../models/userDetails";
import { CommentStmt } from "@angular/compiler";

@Component({
  selector: "app-search",
  templateUrl: "./search.component.html",
  styleUrls: ["./search.component.css"]
})
export class SearchComponent implements OnInit {
  @ViewChild("f") signupForm: NgForm;
  bank: String = "";
  branch: String = "";
  account = "";

  results: UserDetails[] = [];

  constructor(private bankService: BankService) {}

  ngOnInit() {}
  onSubmit() {
    this.bank = this.signupForm.value.bank;
    this.branch = this.signupForm.value.branch;
    this.account = this.signupForm.value.account;
    //if no one is selected
    if (
      (this.account == "" && this.branch == "" && this.bank == "") ||
      (this.account == null && this.branch == null && this.bank == null)
    ) {
      alert("give atleast one input");
    }
    //get By bank
    else if (
      (this.account == "" && this.branch == "") ||
      (this.account == null && this.branch == null)
    ) {
      this.bankService.getByBank(this.bank).subscribe(res => {
        this.results = res.results.bindings;

        for (let i = 0; i < this.results.length; i++) {
          console.log(this.results[i].firstName.value);
        }
      });
    }
    //get by branch
    else if (
      (this.account == "" && this.bank == "") ||
      (this.account == null && this.bank == null)
    ) {
      this.bankService.getbybranch(this.branch).subscribe(res => {
        this.results = res.results.bindings;
      });
    }
    //get by account
    else if (
      (this.branch == "" && this.bank == "") ||
      (this.branch == null && this.bank == null)
    ) {
      this.bankService.getbyaccount(this.account[0]).subscribe(res => {
        this.results = res.results.bindings;
      });
    }
    //get by bank and branch
    else if (this.account == "" || this.account == null) {
      this.bankService
        .getbybankbranch(this.bank, this.branch)
        .subscribe(res => {
          this.results = res.results.bindings;
        });
    }
    //get by bank and account
    else if (this.branch == "" || this.branch == null) {
      this.bankService
        .getbybankaccount(this.bank, this.account[0])
        .subscribe(res => {
          this.results = res.results.bindings;
        });
    }
    //get by branch and account
    else if (this.bank == "" || this.bank == null) {
      this.bankService
        .getbybranchaccount(this.branch, this.account[0])
        .subscribe(res => {
          this.results = res.results.bindings;
        });
    }
    //get by bank, branch and accout
    else {
      this.bankService
        .getbybankbranchaccount(this.bank, this.branch, this.account[0])
        .subscribe(res => {
          this.results = res.results.bindings;
        });
    }

    this.signupForm.reset();
  }
}
