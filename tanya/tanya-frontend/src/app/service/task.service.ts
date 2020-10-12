import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TaskEditerDTO } from '../model/task.editer.modal';
import { TaskDTO } from '../model/task.dto.modal';
import { backendUrl } from '../app.constant';
import { TaskMiniDTO } from '../model/task.mini.dto';

@Injectable()
export class TaskService {

    private get baseUrl(): string {
        return backendUrl + 'api/';
    }

    constructor(private http: HttpClient) { }

    public create(task: TaskEditerDTO): Observable<TaskDTO> {
        return this.http.put<TaskDTO>(this.baseUrl + 'task', task);
    }

    public update(id: number, task: TaskEditerDTO): Observable<TaskDTO> {
        const params = new HttpParams().set('id', id.toString());
        return this.http.post<TaskDTO>(this.baseUrl + 'task', task, {
            params: params
        });
    }

    public getTask(id: number): Observable<TaskDTO> {
        const params = new HttpParams().set('id', id.toString());
        return this.http.get<TaskDTO>(this.baseUrl + 'task', {
            params: params
        });
    }

    public getAllOwnCreatedTask(): Observable<TaskMiniDTO[]> {
        return this.http.get<TaskMiniDTO[]>(this.baseUrl + 'task/created');
    }

    public getAllOwnResponsibledTask(): Observable<TaskMiniDTO[]> {
        return this.http.get<TaskMiniDTO[]>(this.baseUrl + 'task/responsibled');
    }

    public getTasksByProjectId(projectId: number): Observable<TaskMiniDTO[]> {
        const params = new HttpParams().set('projectId', projectId.toString());
        return this.http.get<TaskMiniDTO[]>(this.baseUrl + 'task/project', {
            params: params
        });
    }

    public delete(id: number): Observable<void> {
        const params = new HttpParams().set('id', id.toString());
        return this.http.delete<void>(this.baseUrl + 'task', {
            params: params
        });
    }
}
