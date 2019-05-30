import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GroupDTO } from '../model/group.dto.modal';
import { Observable } from 'rxjs';

@Injectable()
export class GroupService {


    private get baseUrl(): string {
        return '//localhost:8080/api/';
    }

  constructor( private http: HttpClient) {}

    public create(group: GroupDTO): Observable<GroupDTO> {
        const params = new HttpParams();
        return this.http.put<GroupDTO>(this.baseUrl + 'group', group);
    }

    public update(group: GroupDTO): Observable<GroupDTO> {
        const params = new HttpParams();
        return this.http.post<GroupDTO>(this.baseUrl + 'group', group);
    }

    public getGroup(id: number): Observable<GroupDTO> {
        const params = new HttpParams();
        return this.http.get<GroupDTO>(this.baseUrl + 'group' + '/' + id );
    }

    public delete(id: number): Observable<void> {
        const params = new HttpParams();
        return this.http.delete<void>(this.baseUrl + 'group/' + id );
    }

    public getUserCreatedGroups(): Observable<GroupDTO[]> {
        const params = new HttpParams();
        return this.http.get<GroupDTO[]>(this.baseUrl + 'group/created' );
    }

    public getGroupsWhereUserAttendant(): Observable<GroupDTO[]> {
        const params = new HttpParams();
        return this.http.get<GroupDTO[]>(this.baseUrl + 'group/attendanted' );
    }

    public getUserNameinGroup(id: number): Observable<string[]> {
        const params = new HttpParams();
        return this.http.get<string[]>(this.baseUrl + 'group/users/' + id );
    }

    public addUserForGroup(id: number, username: string): Observable<void> {
        const params = new HttpParams();
        return this.http.get<void>(this.baseUrl + 'group/' + id + '/user/' + username);
    }
}
