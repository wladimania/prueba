import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboard.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { UserDetails } from '../../models/user-details.model';

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
