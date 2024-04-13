import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-recover-password',
  templateUrl: './recover-password.component.html',
  styleUrls: ['./recover-password.component.css']
})
export class RecoverPasswordComponent {
  recoverForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    newPassword: ['', [Validators.required, Validators.minLength(8)]]
  });

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  onSubmitRecover(): void {
    const email = this.recoverForm.get('email')?.value;
    const newPassword = this.recoverForm.get('newPassword')?.value;

    if (email && newPassword && this.recoverForm.valid) {
      this.authService.cambiarContrasena(email, newPassword).subscribe({
        next: (response) => {
          console.log('Contraseña actualizada correctamente.', response);
          alert('Tu contraseña ha sido actualizada correctamente.');
        },
        error: (error) => {
          console.error('Error:', error);
          alert('Error al cambiar la contraseña. Por favor, verifica los datos e intenta de nuevo.');
        }
      });
    } else {
      alert('Por favor, asegúrate de que todos los campos están correctamente llenados y validados.');
    }
  }
}
