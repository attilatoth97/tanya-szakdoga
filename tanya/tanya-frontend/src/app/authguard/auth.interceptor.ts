import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor() {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let idToken;
    idToken = window.localStorage.getItem('id_token');
    if (idToken) {
        const cloned = req.clone({
            setHeaders: {
                Authorisation: 'Token ' + idToken
            }
        });
        return next.handle(cloned);
    }
    return next.handle(req);
  }
}
