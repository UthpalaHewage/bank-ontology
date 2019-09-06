import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/internal/operators/map";

@Injectable({
  providedIn: "root"
})
export class BankService {
  constructor(private http: HttpClient) {}
  data = {
    bankName: "",
    branchLocation: "",
    accountType: ""
  };
  getByBank(bank) {
    this.data.bankName = bank;
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http
      .post<any>("http://localhost:8080/getbybank", this.data, {
        headers
      })
      .pipe(
        map(res => {
          return res;
        })
      );
  }

  getbybranch(branch) {
    this.data.branchLocation = branch;
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http
      .post<any>("http://localhost:8080/getbybranch", this.data, {
        headers
      })
      .pipe(
        map(res => {
          return res;
        })
      );
  }

  getbyaccount(account) {
    this.data.accountType = account;
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http
      .post<any>("http://localhost:8080/getbyaccount", this.data, {
        headers
      })
      .pipe(
        map(res => {
          return res;
        })
      );
  }

  getbybankbranch(bank, branch) {
    this.data.bankName = bank;
    this.data.branchLocation = branch;
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http
      .post<any>("http://localhost:8080/getbybankbranch", this.data, {
        headers
      })
      .pipe(
        map(res => {
          return res;
        })
      );
  }

  getbybankaccount(bank, account) {
    this.data.bankName = bank;
    this.data.accountType = account;
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http
      .post<any>("http://localhost:8080/getbybankaccount", this.data, {
        headers
      })
      .pipe(
        map(res => {
          return res;
        })
      );
  }

  getbybranchaccount(branch, account) {
    this.data.branchLocation = branch;
    this.data.accountType = account;
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http
      .post<any>("http://localhost:8080/getbybranchaccount", this.data, {
        headers
      })
      .pipe(
        map(res => {
          return res;
        })
      );
  }

  getbybankbranchaccount(bank, branch, account) {
    this.data.bankName = bank;
    this.data.branchLocation = branch;
    this.data.accountType = account;
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http
      .post<any>("http://localhost:8080/getbybankbranchaccount", this.data, {
        headers
      })
      .pipe(
        map(res => {
          return res;
        })
      );
  }

  getByCustomerId(id: string) {
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    return this.http.get("http://localhost:8080/getByCustomerId/" + id, {
      headers
    });
  }
}
