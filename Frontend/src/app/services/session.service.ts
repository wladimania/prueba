import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Sessions } from '../models/sessions.model';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private sessionsUrl = 'api/sessions'; // URL to web api

  constructor(private http: HttpClient) {}

  // Methods to handle session logs

}
