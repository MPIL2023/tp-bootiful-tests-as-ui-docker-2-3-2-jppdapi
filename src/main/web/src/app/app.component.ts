import { Component } from '@angular/core';
import {AdderService} from "./services/adder.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'jppdapi';

  constructor(private _adder: AdderService) {
  }


  public getCurrent(): void {
    this._adder.current().subscribe((current) => {
      document.getElementById('current')!!.innerText = current.toString(10);
    });
  }

  public add(): void {
    this._adder.add(
      parseInt((document.getElementById('add-input') as any)!!.value, 10)
    ).subscribe((newValue) => {
      document.getElementById('add')!!.innerText = newValue.toString(10);
    });
  }

  public acc(): void {
    this._adder.acc(
      parseInt((document.getElementById('acc-input') as any)!!.value, 10)
    ).subscribe((newValue) => {
      document.getElementById('acc')!!.innerText = newValue.toString(10);
    });
  }
}
