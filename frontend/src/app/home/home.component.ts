import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Country } from '../country';
import { DataService } from '../data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  countryControl = this.formBuilder.group({
    countryName: new FormControl({ disabled: false, value: '' }, [Validators.required])
  });
  countries: Country[] = [];

  constructor(private dataService: DataService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {

    this.dataService.getCountries().subscribe(
      (countries: Country[]) => {
        this.countries.push(...countries);
      });
  }

  public getHolidays(): void {

    // Find the country from the countries array
    let record = this.countryControl.value
    // Don't forget country is an array so treat it as such
    let country = this.countries.filter(c => c.name === this.countryControl.value.countryName);
    this.router.navigate(['/view/'+ country[0].code]);
  }
  
}
