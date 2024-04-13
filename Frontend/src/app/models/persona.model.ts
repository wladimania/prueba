import {User} from "./user.model";

export interface Persona {
  idPersona: number;
  nombres: string;
  apellido: string;
  identificacion: string;
  fechaNacimiento: string; // Se usa un string en formato ISO para las fechas
  // Suponiendo que UsuariosEntity se refiera a un tipo 'User', omitido aquí por brevedad
  usuariosByIdPersona?: User[];
}
