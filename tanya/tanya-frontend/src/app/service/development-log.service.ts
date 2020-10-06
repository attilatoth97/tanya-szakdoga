import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { backendUrl } from '../app.constant';
import { DevelopmentLogCreateDTO } from '../model/development-log-create-dto.model';
import { DevelopmentLogDTO } from '../model/development-log.dto.model';

@Injectable()
export class DevelopmentLogService {

    private get baseUrl(): string {
        return backendUrl + 'api/';
    }

    constructor(private http: HttpClient) { }

    public create(developmentLog: DevelopmentLogCreateDTO): Observable<DevelopmentLogDTO> {
        return this.http.post<DevelopmentLogDTO>(this.baseUrl + 'development-log', developmentLog);
    }

    public update(id: number, developmentLog: DevelopmentLogCreateDTO): Observable<DevelopmentLogDTO> {
        const params = new HttpParams().set('id', id.toString());
        return this.http.put<DevelopmentLogDTO>(this.baseUrl + 'development-log', {
            body: developmentLog,
            params: params
        });
    }

    public getDevelopmentLog(id: number): Observable<DevelopmentLogDTO> {
        const params = new HttpParams().set('id', id.toString());
        return this.http.get<DevelopmentLogDTO>(this.baseUrl + 'development-log/', {
            params: params
        });
    }

    public findAllByTaskId(taskId: number): Observable<DevelopmentLogDTO[]> {
        const params = new HttpParams().set('id', taskId.toString());
        return this.http.get<DevelopmentLogDTO[]>(this.baseUrl + 'development-log/task/', {
            params: params
        });
    }

    public findAllByProjectId(projectId: number): Observable<DevelopmentLogDTO[]> {
        const params = new HttpParams().set('id', projectId.toString());
        return this.http.get<DevelopmentLogDTO[]>(this.baseUrl + 'development-log/project/', {
            params: params
        });
    }

    public findAllByCurrentUserId(): Observable<DevelopmentLogDTO[]> {
        return this.http.get<DevelopmentLogDTO[]>(this.baseUrl + 'development-log/current-user');
    }

    public delete(id: number): Observable<void> {
        const params = new HttpParams().set('id', id.toString());
        return this.http.delete<void>(this.baseUrl + 'development-log/', {
            params: params
        });
    }
}
