import { PersonDTO } from './person.dto.modal';

export interface UserEditerDTO {
    id: number;
    userName: string;
    password: string;
    person: PersonDTO;
}
