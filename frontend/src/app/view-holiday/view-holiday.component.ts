import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Country } from '../country';
import { DataService } from '../data.service';
import { Holiday } from '../holiday';

@Component({
  selector: 'app-view-holiday',
  templateUrl: './view-holiday.component.html',
  styleUrls: ['./view-holiday.component.sass']
})
export class ViewHolidayComponent implements OnInit {

  name: String = "";
  code: String = "";

  displayedColumns: string[] = ['Holiday', 'Date'];
  dataSource: MatTableDataSource<Holiday> = new MatTableDataSource<Holiday>();

  @ViewChild(MatTable, { static: true }) table!: MatTable<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;


  constructor(private dataService: DataService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    // Get the Agent id from the route
    const code = this.route.snapshot.paramMap.get('code');
    this.getHolidays({ "code": code } as Country);
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