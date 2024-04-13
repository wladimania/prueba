import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboard.service';
import { AuthService } from '../../services/auth.service'; // Asegúrate de tener este servicio
import { Router } from '@angular/router'; // Para redirigir al usuario después del logout
import { UserDetails } from '../../models/user-details.model';
@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  blockedUsersCount: number = 0;
  activeUsersCount: number = 0; // Definición de la variable
  userDetails: UserDetails | null = null;
  constructor(
    private dashboardService: DashboardService,
    private authService: AuthService, // Inyecta el AuthService
    private router: Router // Inyecta el Router
  ) { }

  ngOnInit(): void {
    this.loadUserDetails();
    this.loadActiveUsers();
    this.blockedUsersCount = 0;
  }
  loadUserDetails() {
    const userDetailsJson = localStorage.getItem('userDetails');
    if (userDetailsJson) {
      this.userDetails = JSON.parse(userDetailsJson);
    }
  }
  loadActiveUsers(): void {
    this.dashboardService.getActiveUsersCount().subscribe(
      (count: number) => {
        this.activeUsersCount = count; // Actualización de la variable basada en los datos del servicio
      },
      error => {
        console.error('Error loading active users count', error);
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


}
