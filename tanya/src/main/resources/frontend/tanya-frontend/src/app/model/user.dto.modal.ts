import { PersonDTO } from './person.dto.modal';

export interface UserDTO {
    id: number;
    userName: string;
    person: PersonDTO;
}
