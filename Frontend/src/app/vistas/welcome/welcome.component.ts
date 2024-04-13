import { Component, OnInit } from '@angular/core';
interface User {
  name: string;
  // puedes añadir más propiedades según sea necesario
}
@Component({
  selector: 'app-welcome',
  standalone: false,
  templateUrl: 'welcome.component.html',
  styleUrl: 'welcome.component.css'
})
export class WelcomeComponent {
  user: User; // Agrega la propiedad user aquí
  lastLoginDate: Date;
  // ...

  constructor() {
    // Inicializa las propiedades
    this.lastLoginDate = new Date(); // o la fecha que corresponda
    this.user = {
      name: 'Nombre de Usuario', // Este valor se mostrará como placeholder
      // inicializa otras propiedades si son necesarias
    };
  }
  ngOnInit(): void {
    // Aquí normalmente obtendrías los datos del usuario de un servicio
    // y actualizarías la propiedad user con esos datos.
  }
}
