import { OnInit, Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthHelperService } from 'src/app/auth-helper.service';
import { Location } from '@angular/common';
import { ROUTES } from '../sidebar/sidebar.component';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {

    private listTitles: any[];
    private readonly personDetailsRoute = 'person-details';
    location: Location;
    constructor(private route: Router,
        private authHelperService: AuthHelperService,
        location: Location) {
        this.location = location;

    }

    ngOnInit(): void {
        this.listTitles = ROUTES.filter(listTitle => listTitle);
    }

    navigateToProfilModifyPage() {
        this.route.navigate(['/', this.personDetailsRoute]);
    }

    logout() {
        this.authHelperService.logout();
    }

    getTitle() {
        let title = this.location.prepareExternalUrl(this.location.path());

        if (title.charAt(0) === '#') {
            title = title.slice(1);
        }

        for (let item = 0; item < this.listTitles.length; item++) {
            const path = this.listTitles[item].path as string;
            if (title.includes(path)) {
                return this.listTitles[item].title;
            }
        }
        return 'Kezdőlap';
    }

}
