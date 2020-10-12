import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { backendUrl } from '../app.constant';

@Injectable()
export class StatisticsService {

    private get baseUrl(): string {
        return backendUrl + 'api/statistics/';
    }

    constructor(private http: HttpClient) { }

    public getGroupNumber(): Observable<number> {
        return this.http.get<number>(this.baseUrl + 'group-number');
    }

    public getProjectNumber(): Observable<number> {
        return this.http.get<number>(this.baseUrl + 'project-number');
    }

    public getDevelopmentLogSum(): Observable<number> {
        return this.http.get<number>(this.baseUrl + 'development-log-sum');
    }

    public getCreatedTaskNumber(): Observable<number> {
        return this.http.get<number>(this.baseUrl + 'created-task-number');
    }

    public getCompletedTaskNumber(): Observable<number> {
        return this.http.get<number>(this.baseUrl + 'completed-task-number');
    }

    public getDevelopmentLog(): Observable<Map<string, number>> {
        return this.http.get<Map<string, number>>(this.baseUrl + 'development-log');
    }

    public getDevelopmentLogByGroup(): Observable<Map<string, number>> {
        return this.http.get<Map<string, number>>(this.baseUrl + 'development-log-by-group');
    }

}
