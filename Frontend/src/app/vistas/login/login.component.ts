import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from "../../services/auth.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  showPassword = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router  // Router inyectado correctamente
  ) {
    this.loginForm = this.fb.group({
      userName: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(20), Validators.pattern(/^(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]*$/)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[A-Z])(?=.*[!@#$&*])[^\s]*$/)]]
    });
  }

  // Cambia la visibilidad de la contraseña
  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  // Función que maneja el inicio de sesión
  onLogin(): void {
    if (this.loginForm.valid) {
      // Si el formulario es válido, intenta iniciar sesión
      this.authService.login(this.loginForm.value.userName, this.loginForm.value.password)
        .subscribe({
          next: (user) => {
            // Si el inicio de sesión es exitoso, redirige al dashboard
            this.router.navigate(['/dashboard']);
          },
          error: (error) => {
            // Si ocurre un error, muestra un mensaje en consola
            console.error('Error during login', error);
          }
        });
    } else {
      // Si el formulario no es válido, muestra los errores
      console.error('Form is not valid', this.loginForm.errors);
    }
  }

  // Redirige al usuario a la página de recuperación de contraseña
  onForgotPassword(): void {
    alert('Función llamada correctamente');
    this.router.navigate(['/recover-password'])
      .then(() => {
        console.log('Redireccionado a la página de recuperación de contraseña');
      })
      .catch(err => {
        console.error('Error al redirigir a la página de recuperación de contraseña', err);
      });
  }

}
