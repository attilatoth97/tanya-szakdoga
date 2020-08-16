import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserService } from '../service/user.service';
import { Observable } from 'rxjs';

export class AuthguardGuard implements CanActivate {

    public isLogged: boolean;
    public isLogged_ = true;

    constructor() { }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        this.refresh();
        return this.isLogged;
    }

    refresh() {
        this.isLogged = window.localStorage.getItem('id_token') ? true : false;
    }
}
