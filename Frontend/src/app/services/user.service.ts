import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersUrl = 'api/users'; // URL to web api

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  // Add methods for CRUD operations

  // Method for generating and verifying unique emails
  generateEmail(firstName: string, lastName: string): string {
    // ... logic to generate email
    return `${firstName}.${lastName}@mail.com`;
  }
}
