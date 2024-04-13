import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from '../models/user.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = `${environment.apiBaseUrl}/session/login`;
  private logoutUrl = `${environment.apiBaseUrl}/session/logout`;
  private recoverPasswordUrl = `${environment.apiBaseUrl}/session/cambiarContrasena`;

  constructor(private http: HttpClient, private router: Router) {}

  cambiarContrasena(email: string, nuevaContrasena: string): Observable<any> {
    const body = { mail: email, nuevaContrasena };
    return this.http.post(this.recoverPasswordUrl, body).pipe(
      catchError(this.handleError)
    );
  }



  login(userName: string, password: string): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.loginUrl, { userName, password }, { observe: 'response' }).pipe(
      tap(response => {
        if (response.status === 200 && response.body) {
          localStorage.setItem('idUsuario', response.body.idUsuario.toString());
          this.router.navigate(['/dashboard']);
        }
      }),
      catchError(this.handleError)
    );
  }

  logout(idUsuario: number | null): Observable<any> {
    if (idUsuario === null) {
      console.error('User ID is null, cannot logout');
      this.router.navigate(['/login']);
      return throwError('User ID is null, cannot logout');
    }
    return this.http.post(this.logoutUrl, { idUsuario }).pipe(
      tap(() => {
        localStorage.removeItem('idUsuario');
        this.router.navigate(['/login']);
      }),
      catchError(this.handleError)
    );
  }

  private handleError(error: any) {
    return throwError(error);
  }
}
