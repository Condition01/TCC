import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  bannerSubject: Subject<any> = new Subject<any>();

  constructor() { }

  ngOnInit(): void {
    this.bannerSubject.next(true);
  }

}
