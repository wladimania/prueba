import { Component, OnInit, LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';

interface User {
  idUsuario: number;
  userName: string;
  mail: string;
  personaByPersonaIdPersona2: {
    identificacion: string;
    apellido: string;
    idPersona: number;
    nombres: string;
    fechaNacimiento: string;
  };
  rolUsuariosByIdUsuario: any[];
}


// Registro de la localización en español
registerLocaleData(localeEs);

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css'],
  providers: [
    { provide: LOCALE_ID, useValue: 'es-ES' } // Configura el locale a español de España
  ]
})
export class WelcomeComponent implements OnInit {
  userDetails: User | null = null;

  constructor() {}

  ngOnInit(): void {
    const userDetails = localStorage.getItem('userDetails');
    if (userDetails) {
      this.userDetails = JSON.parse(userDetails);
    }
  }
}
