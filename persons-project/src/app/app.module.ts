import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

// SPRING BOOT REST MODELS
import { HttpClientModule } from '@angular/common/http';
import { PersonService } from './shared/person/person.service';
import { PersonsListComponent } from './persons-list/persons-list.component';

// MATERIAL
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PersonEditComponent } from './person-edit/person-edit.component';

//ROUTES
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  { path: '', redirectTo: '/person-list', pathMatch: 'full' },
  {
    path: 'person-list',
    component: PersonsListComponent
  },
  {
    path: 'person-add',
    component: PersonEditComponent
  },
  {
    path: 'person-edit/:id',
    component: PersonEditComponent
  }
];

// MODULE
@NgModule({
  declarations: [
    AppComponent,
    PersonsListComponent,
    PersonEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    RouterModule.forRoot(appRoutes),
    FormsModule    
  ],
  providers: [PersonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
