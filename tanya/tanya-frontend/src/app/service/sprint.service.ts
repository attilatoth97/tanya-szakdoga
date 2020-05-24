import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SprintEditorDTO } from '../model/sprint.editor.dto';
import { SprintDTO } from '../model/sprint.dto';
import { Observable } from 'rxjs';
import { backendUrl } from '../app.constant';
import { SprintMapDTO } from '../model/sprint.map.dto';

@Injectable()
export class SprintService {

    private get baseUrl(): string {
        return backendUrl + 'api/';
    }

    constructor(private http: HttpClient) { }

    public create(sprint: SprintEditorDTO): Observable<SprintDTO> {
        const params = new HttpParams();
        return this.http.put<SprintDTO>(this.baseUrl + 'sprint', sprint);
    }

    public getTheProjectsSprints(id: number): Observable<SprintDTO[]> {
        const params = new HttpParams();
        return this.http.get<SprintDTO[]>(this.baseUrl + 'sprint' + '/' + id);
    }

    public getTheProjectsMapSprints(id: number): Observable<SprintMapDTO[]> {
        const params = new HttpParams();
        return this.http.get<SprintDTO[]>(this.baseUrl + 'sprint/map/' + id);
    }

}
