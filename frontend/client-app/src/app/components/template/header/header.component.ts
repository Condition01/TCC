import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  url: string

  constructor(private router: Router) { 
    this.url = this.router.url;
    console.log(this.url)
  }

  ngOnInit(): void {
    
  }

}
