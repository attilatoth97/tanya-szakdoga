import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { HttpRequest, HttpHandler} from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Router } from '@angular/router';
import { map, catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable()
export class ResponseInterceptor implements HttpInterceptor {
    constructor(private toastr: ToastrService, private router: Router) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(
            tap((event: HttpEvent<any>) => {
            return event;
        }),
        catchError((error: HttpErrorResponse) => {
            if (error.status === 400) {
                this.toastr.error(error.error.message);

                if (error.error.errors) {
                    error.error.errors.array.forEach(element => {
                        this.toastr.error(element.message);
                    });
                }
            }

            if (error.status === 401) {
                this.router.navigate(['/', 'login']);
            }

            if (error.status === 403) {
                this.toastr.error('Nincs jogosultságod az oldal megtekintésére!');

            }

            if (error.status === 404) {
                this.toastr.error('Oldal nem található!');
            }

            if (error.status === 500) {
                this.toastr.error(error.error.message);
                
                if (error.error.errors) {
                    error.error.errors.array.forEach(element => {
                        this.toastr.error(element.message);
                    });
                }
            }

            return  throwError(error);
        }));
    }
}
