import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserEditerDTO } from '../model/user.editer.dto.modal';
import { UserDTO } from '../model/user.dto.modal';
import { Observable } from 'rxjs/internal/Observable';
import { backendUrl } from '../app.constant';


@Injectable()
export class UserService {

    private get baseUrl(): string {
        return backendUrl;
    }

    constructor(private http: HttpClient) { }

    public getUserLoggedIn(): Observable<boolean> {
        return this.http.get<boolean>(this.baseUrl + 'api/user-logged');
    }

    public create(user: UserEditerDTO): Observable<UserDTO> {
        return this.http.put<UserDTO>(this.baseUrl + 'registration', user);
    }

    public update(user: UserEditerDTO): Observable<UserDTO> {
        return this.http.post<UserDTO>(this.baseUrl + 'api/user', user);
    }

    public getLoggedUser(): Observable<UserEditerDTO> {
        return this.http.get<UserEditerDTO>(this.baseUrl + 'api/logged-user');
    }

    public getTest(): Observable<string> {
        return this.http.get<string>(this.baseUrl + 'api/test');
    }
}
