// src/app/app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'; // Importa RouterModule y Routes
import { HttpClientModule } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';

// Importa otros componentes y servicios que uses
import { LoginComponent } from './vistas/login/login.component';
import { DashboardComponent } from './vistas/dashboard/dashboard.component';
import { AuthService } from './services/auth.service';
import { DashboardService } from './services/dashboard.service';
import { UserMaintenanceComponent } from "./vistas/usermaintenance/usermaintenance.component";
import { SessionService } from "./services/session.service";
import { UserService } from "./services/user.service";
import { WelcomeComponent } from "./vistas/welcome/welcome.component";
import {RecoverPasswordComponent} from "./vistas/recover-password/recover-password.component";


const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'recover-password', component: RecoverPasswordComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    UserMaintenanceComponent,
    WelcomeComponent,
    RecoverPasswordComponent,
  ],
  imports: [

    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes, { enableTracing: true }),
    ReactiveFormsModule,
  ],
  providers: [
    AuthService,
    DashboardService,
    SessionService,
    UserService,
  ],

  bootstrap: [AppComponent] // El componente raíz que inicia la aplicación
})
export class AppModule { }
