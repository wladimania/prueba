import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-maintenance',
  standalone: false,
  templateUrl: 'usermaintenance.component.html',
  styleUrls: ['usermaintenance.component.css']
})
export class UserMaintenanceComponent {
  user: any = {};
  onLoadUsers(): void {
    // Implementa la lógica de carga de usuarios
  }

  onSubmitUser(): void {
    // Implementa la lógica de envío de formulario
  }
  constructor(private userService: UserService) {}

  // Implement logic for handling file upload and CRUD

}
