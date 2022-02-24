import { Component, OnInit, ViewChild } from '@angular/core';
import { DataService } from './data.service';
import { Country } from './country';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Holiday } from './holiday';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {

  title = 'frontend';
  name: String = "";
  code: String = "";

  displayedColumns: string[] = ['Holiday', 'Date'];
  dataSource: MatTableDataSource<Holiday> = new MatTableDataSource<Holiday>();

  @ViewChild(MatTable, { static: true }) table!: MatTable<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {     
    this.getHolidays({ "name": "", "code": "IL", "holidays": []});
  }

  public getHolidays(country: Country): void {

    this.dataService.getPublicHolidays(country).subscribe(
      country => {
        this.code = country.code;
        this.name = country.name;
        this.dataSource = new MatTableDataSource<Holiday>(country.holidays);
        this.dataSource.paginator = this.paginator;
      }
    );

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
