import { Component, OnInit } from '@angular/core';
import { PersonService } from '../shared/person/person.service';

@Component({
  selector: 'app-persons-list',
  templateUrl: './persons-list.component.html',
  styleUrls: ['./persons-list.component.css']
})
export class PersonsListComponent implements OnInit {
  persons: Array<any>;
  
  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.personService.getAll().subscribe(data => {
      this.persons = data;
    });
  }
}
