import { OnInit, Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthguardGuard } from 'src/app/authguard/authguard.guard';

@Component({
    selector: 'app-header-component',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css'],
    providers: [AuthguardGuard]

})
export class HeaderComponent implements OnInit {

    idToken: any;
    constructor(private router: Router, private toast: ToastrService, public auth: AuthguardGuard) {}

    ngOnInit() {
        this.auth.refresh();
        console.log('looged ' + this.auth.isLogged);

    }

    logout() {
        window.localStorage.clear();
        this.toast.success('Kijelentkezés sikeres volt!');
        this.router.navigateByUrl('/login');
        this.ngOnInit();

    }
}
