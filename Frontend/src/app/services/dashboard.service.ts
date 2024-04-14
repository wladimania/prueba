// dashboard.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private usuariosUrl = `${environment.apiBaseUrl}/usuarios/admin/usuarios`;

  constructor(private http: HttpClient) { }

  getUsuariosList(idUsuario: number): Observable<UserDetails[]> {
    const url = `${this.usuariosUrl}/${idUsuario}`;
    return this.http.post<UserDetails[]>(url, { idUsuario });
  }
}

