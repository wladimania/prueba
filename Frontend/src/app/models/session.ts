export interface Sessions {
  idSesiones: number;
  fechaIngreso: string; // Se usa un string en formato ISO para las fechas
  fechaCierre: string; // Se usa un string en formato ISO para las fechas
  usuariosIdUsuario: number;
  // Suponiendo que UsuariosEntity sea equivalente a User
  // usuariosByUsuariosIdUsuario?: User;
}
