import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { backendUrl } from '../app.constant';
import { LoginDTO } from '../model/login.dto.modal';

@Injectable()
export class AuthService {


    private get baseUrl(): string {
        return backendUrl + '';
    }

  constructor( private http: HttpClient) {}

    public getToken(login: LoginDTO): Observable<string[]> {
        return this.http.post<string[]>(this.baseUrl + 'token', login);
    }
}
