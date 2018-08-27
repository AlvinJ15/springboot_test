import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PersonService {
  public API = '//localhost:8080/api';
  public PERSON_API = this.API + '/persons';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.PERSON_API);

  }

  get(id: string) {
    return this.http.get(this.PERSON_API + '/' + id);
  }

  save(person: any): Observable<any> {
    let result: Observable<Object>;
    console.log("url:" + person['href']);
    if (person['href']) {
      result = this.http.put(person.href, person);
    } else {
      result = this.http.post(this.PERSON_API, person);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}