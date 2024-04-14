import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboard.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { UserDetails } from '../../models/user-details.model';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  userDetails: UserDetails | null = null;
  usersList: UserDetails[] = [];

  constructor(
    private dashboardService: DashboardService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadUserDetails();
    this.loadUsersList();
  }

  loadUserDetails() {
    const userDetailsJson = localStorage.getItem('userDetails');
    if (userDetailsJson) {
      this.userDetails = JSON.parse(userDetailsJson);
    }
  }

  loadUsersList(): void {
    const idUsuario = this.getCurrentidUsuario();
    this.dashboardService.getUsuariosList(idUsuario).subscribe(
      (users: UserDetails[]) => {
        this.usersList = users;
      },
      error => {
        console.error('Error loading users list', error);
        // Manejar el error aquí si es necesario
      }
    );
  }

  logout(): void {
    const idUsuario = this.getCurrentidUsuario();
    this.authService.logout(idUsuario).subscribe(
      response => {
        console.log('Logout successful, navigating to login');
        this.router.navigateByUrl('/login').then(success => {
          console.log('Navigation to login successful:', success);
        }).catch(error => {
          console.error('Navigation to login failed:', error);
        });
      },
      error => {
        console.error('Error during logout', error);
      }
    );
  }

  private getCurrentidUsuario(): number {
    const userIdString = localStorage.getItem('idUsuario');
    if (userIdString === null) {
      console.error('No user ID found in localStorage');
      this.router.navigate(['/login']);
      return 0;
    }
    return parseInt(userIdString, 10);
  }
  // Función para abrir el selector de archivo
  openFileUploader(): void {
    const fileUploader = document.getElementById('fileUploader');
    if (fileUploader) {
      fileUploader.click();
    }
  }

  handleFileInput(event: any): void {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (e: any) => {
      const data = new Uint8Array(e.target.result);
      const workbook = XLSX.read(data, { type: 'array' });
      const sheetName = workbook.SheetNames[0];
      const worksheet = workbook.Sheets[sheetName];
      const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

      jsonData.forEach((row: any) => {
        if (row.length === 0) {
          console.log("Fila vacía, saltando...");
          return; // Saltar la iteración si la fila está vacía
        }

        console.log("Fila actual:", row);
        const usuario: UserDetails = {
          userName: row[0],
          mail: row[1],
          password: row[2],
          status: row[3],
          intentoFallido: row[4],
          personaByPersonaIdPersona2: {
            identificacion: row[5],
            apellido: row[6],
            nombres: row[7],
            idPersona: 0, // Valor predeterminado para idPersona
            fechaNacimiento: row[8] // Ajustar el índice para la fecha de nacimiento si es necesario
          }
        };

        this.createUser(usuario);
      });



    };

    reader.readAsArrayBuffer(file);
  }



  createUser(usuario: UserDetails): void {
    this.dashboardService.createUser(usuario).subscribe(
      response => {
        console.log('Usuario creado exitosamente:', response);
        // Agrega esta línea para imprimir el usuario creado en la consola
        console.log(usuario);
      },
      error => {
        console.error('Error al crear el usuario:', error);
      }
    );
  }



}
