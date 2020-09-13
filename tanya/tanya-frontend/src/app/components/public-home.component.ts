import { OnInit, Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthHelperService } from '../auth-helper.service';

@Component({
    selector: 'app-public-home-component',
    templateUrl: './public-home.component.html'
})
export class PublicHomeComponent implements OnInit, OnDestroy {

    isLogged = false;
    private subs: Subscription;

    constructor(private authHelperService: AuthHelperService, private route: Router) {
        this.subs = this.authHelperService.obs.subscribe(e => {
            this.isLogged = e;
        });
    }

    ngOnInit() {
        this.authHelperService.refresh();
        this.isLogged = this.authHelperService.isLogged;
        if (this.isLogged) {
            this.route.navigate(['/home']);
        } else {
            this.route.navigate(['/login']);
        }
    }

    ngOnDestroy(): void {
        this.subs.unsubscribe();
    }
}
