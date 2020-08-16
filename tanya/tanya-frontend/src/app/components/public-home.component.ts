import { OnInit, Component } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { AuthguardGuard } from '../authguard/authguard.guard';
import { Router } from '@angular/router';

@Component({
    selector: 'app-public-home-component',
    templateUrl: './public-home.component.html',
    providers: [AuthguardGuard]
})
export class PublicHomeComponent implements OnInit {

    constructor(private authGuard: AuthguardGuard, private route: Router) { }

    ngOnInit() {
        this.authGuard.refresh();
        if (this.authGuard.isLogged) {
            this.route.navigate(['/home']);
        } else {
            this.route.navigate(['/login']);
        }
    }
}
