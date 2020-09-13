import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { AuthHelperService } from '../auth-helper.service';

@Injectable()
export class AuthGuard implements CanActivate {

    private readonly loginUrl = 'login';

    constructor(private route: Router, private authHelperService: AuthHelperService) { }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        this.authHelperService.refresh();
        if (!this.authHelperService.isLogged) {
            this.route.navigate(['/', this.loginUrl]);
        }
        return this.authHelperService.isLogged;
    }
}
