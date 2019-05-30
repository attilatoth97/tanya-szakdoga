import { Injectable } from '@angular/core';
import { ProjectEditerDTO } from '../model/project.editer.dto.modal';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ProjectDTO } from '../model/project.dto.modal';
import { backendUrl } from '../app.constant';
import { UserMiniDTO } from '../model/user.mini.dto';

@Injectable()
export class ProjectService {


    private get baseUrl(): string {
        return backendUrl + 'api/';
    }

    constructor( private http: HttpClient) {}

    public create(project: ProjectEditerDTO): Observable<ProjectDTO> {
        const params = new HttpParams();
        return this.http.put<ProjectDTO>(this.baseUrl + 'project', project);
    }

    public update(project: ProjectEditerDTO): Observable<ProjectDTO> {
        const params = new HttpParams();
        return this.http.post<ProjectDTO>(this.baseUrl + 'project', project);
    }

    public getProjectsInGroup(id: number): Observable<ProjectDTO[]> {
        const params = new HttpParams();
        return this.http.get<ProjectDTO[]>(this.baseUrl + 'project' + '/' + id );
    }

    // kell ?
    public delete(id: number): Observable<void> {
        const params = new HttpParams();
        return this.http.delete<void>(this.baseUrl + 'project' + '/' + id );
    }

    public getMiniUserInGroup(id: number): Observable<UserMiniDTO[]> {
        const params = new HttpParams();
        return this.http.get<UserMiniDTO[]>(this.baseUrl + 'project/' + id + '/user' );
    }

}
