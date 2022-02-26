import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Country } from './country';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpClient: HttpClient) { }

  public getPublicHolidays(country: Country): Observable<Country> {

    const url = environment.holidayApi + "/holidays/find";

    return this.httpClient.post<Country>(url, country).pipe();
  }

  public getCountries(): Observable<Country[]> {
    
    const url = environment.holidayApi + "/countries";

    return this.httpClient.get<Country[]>(url).pipe();

  }

}
