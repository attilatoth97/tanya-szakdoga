import { Injectable } from '@angular/core';
import { backendUrl } from '../app.constant';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CalendarEditerDTO } from '../model/calendar.editer.dto.modal';
import { CalendarDTO } from '../model/calendar.dto.modal';

@Injectable()
export class CalendarService {
    private get baseUrl(): string {
        return backendUrl + 'api/';
    }

    constructor( private http: HttpClient) {}

    public create(comment: CalendarEditerDTO): Observable<CalendarDTO> {
        const params = new HttpParams();
        return this.http.put<CalendarDTO>(this.baseUrl + 'calendar', comment);
    }

    public update(comment: CalendarEditerDTO): Observable<CalendarDTO> {
        const params = new HttpParams();
        return this.http.post<CalendarDTO>(this.baseUrl + 'calendar', comment);
    }

    public getCalendarDates(id: number): Observable<CalendarDTO[]> {
        const params = new HttpParams();
        return this.http.get<CalendarDTO[]>(this.baseUrl + 'calendar/project/' + id);
    }

    public getCalendarDate(id: number): Observable<CalendarDTO> {
        const params = new HttpParams();
        return this.http.get<CalendarDTO>(this.baseUrl + 'calendar/' + id);
    }
}
