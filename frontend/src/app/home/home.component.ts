import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Country } from '../country';
import { DataService } from '../data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  countryControl = this.formBuilder.group({
    countryName:  new FormControl({ disabled: false, value: '' }, [Validators.required])
  });
  countries: Country[] = [];

  constructor(private dataService: DataService , private formBuilder: FormBuilder) { }

  ngOnInit(): void {

    this.dataService.getCountries().subscribe(
      (countries: Country[]) => {
        this.countries.push(...countries);
      });
  }

}
