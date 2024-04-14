export interface UserDetails {
  userName: string;
  idUsuario?: number | null;
  mail: string;
  password: string;
  status: string;
  intentoFallido: number;
  personaByPersonaIdPersona2: {
    identificacion: string;
    apellido: string;
    nombres: string;
    idPersona: number;
    fechaNacimiento: string;
  };
}
