export interface User {
  idUsuario: number;
  userName: string;
  password: string;
  mail: string;
  sessionActive: string;
  personaIdPersona2: number;
  status: string;
  intentofallido: number;
  // Suponiendo que RolUsuariosEntity y SessionsEntity se refieran a RolUsuarios y Sessions
  // rolUsuariosByIdUsuario?: RolUsuarios[];
  // sessionsByIdUsuario?: Sessions[];
  // personaByPersonaIdPersona2?: Persona;
}
