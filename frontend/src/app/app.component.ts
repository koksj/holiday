import { Component, OnInit, ViewChild } from '@angular/core';
import { DataService } from './data.service';
import { Country } from './country';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Holiday } from './holiday';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {

  title = 'frontend';

  constructor(private router: Router) { }

  public home(): void {
    this.router.navigate(['/home']);
  }

}
