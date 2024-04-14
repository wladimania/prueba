import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { UserDetails } from '../models/user-details.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private usuariosUrl = `${environment.apiBaseUrl}/usuarios/admin/usuarios`;
  private crearurl = `${environment.apiBaseUrl}/usuarios`;
  constructor(private http: HttpClient) { }
  getUsuariosList(idUsuario: number): Observable<UserDetails[]> {
    const url = `${this.usuariosUrl}/${idUsuario}`;
    return this.http.post<UserDetails[]>(url,{});
  }
  createUser(usuario: UserDetails): Observable<any> {
    return this.http.post<any>(this.crearurl + '/crear', usuario);
  }
}
