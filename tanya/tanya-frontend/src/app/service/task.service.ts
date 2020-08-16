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
        const params = new HttpParams();
        return this.http.put<TaskDTO>(this.baseUrl + 'task', task);
    }

    public update(task: TaskEditerDTO): Observable<TaskDTO> {
        const params = new HttpParams();
        return this.http.post<TaskDTO>(this.baseUrl + 'comment', task);
    }

    public getCommentInTask(id: number): Observable<TaskDTO> {
        const params = new HttpParams();
        return this.http.get<TaskDTO>(this.baseUrl + 'task/' + id);
    }

    public getAllOwnCreatedTask(): Observable<TaskMiniDTO[]> {
        const params = new HttpParams();
        return this.http.get<TaskMiniDTO[]>(this.baseUrl + 'task/created');
    }

    public getAllOwnResponsibledTask(): Observable<TaskMiniDTO[]> {
        const params = new HttpParams();
        return this.http.get<TaskMiniDTO[]>(this.baseUrl + 'task/responsibled');
    }
}
