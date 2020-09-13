import { OnInit, Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthHelperService } from 'src/app/auth-helper.service';
@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {

    private readonly personDetailsRoute = 'person-details';

    constructor(private route: Router, private authHelperService: AuthHelperService) { }

    ngOnInit(): void {
    }

    navigateToProfilModifyPage() {
        this.route.navigate(['/', this.personDetailsRoute]);
    }

    logout() {
        this.authHelperService.logout();
    }

}
