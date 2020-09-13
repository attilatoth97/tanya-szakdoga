import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subject } from 'rxjs';

@Injectable()
export class AuthHelperService {

    private readonly loginUrl = 'login';
    private readonly homeUrl = 'home';

    public isLogged: boolean;
    public isLogged_ = true;

    private subject = new Subject<boolean>();

    public obs = this.subject.asObservable();

    constructor(private route: Router, private toast: ToastrService) {
        this.subject.next(this.isLogged);
    }

    refresh() {
        this.isLogged = window.localStorage.getItem('id_token') ? true : false;
        this.subject.next(this.isLogged);
    }

    login(token: string[]) {
        window.localStorage.setItem('id_token', token[0]);
        this.toast.success('Sikeres bejelentkezés');
        this.route.navigate(['/', this.homeUrl]);
    }

    logout() {
        window.localStorage.clear();
        this.isLogged = false;
        this.subject.next(this.isLogged);
        this.toast.success('Kijelentkezés sikeres volt!');
        this.route.navigate(['/', this.loginUrl]);
    }
}
