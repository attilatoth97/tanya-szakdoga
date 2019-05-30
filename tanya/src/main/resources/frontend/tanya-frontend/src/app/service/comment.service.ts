import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentDTO } from '../model/comment.dto.modal';
import { CommentEditerDTO } from '../model/comment.editer.dto.modal';

@Injectable()
export class CommentService {


    private get baseUrl(): string {
        return '//localhost:8080/api/';
    }

  constructor( private http: HttpClient) {}

    public create(comment: CommentEditerDTO): Observable<CommentDTO> {
        const params = new HttpParams();
        return this.http.put<CommentDTO>(this.baseUrl + 'comment', comment);
    }

    public update(comment: CommentEditerDTO): Observable<CommentDTO> {
        const params = new HttpParams();
        return this.http.post<CommentDTO>(this.baseUrl + 'comment', comment);
    }

    public getCommentInTask(id: number): Observable<CommentDTO[]> {
        const params = new HttpParams();
        return this.http.get<CommentDTO[]>(this.baseUrl + 'comment/user/' + id);

    }
}
