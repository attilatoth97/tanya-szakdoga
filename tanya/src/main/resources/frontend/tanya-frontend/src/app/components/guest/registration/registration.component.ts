import { OnInit, Component } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { UserEditerDTO } from 'src/app/model/user.editer.dto.modal';
import { PersonDTO } from 'src/app/model/person.dto.modal';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
    selector: 'app-registration-component',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.css'],
    providers: [UserService]
})
export class RegistrationComponent {

    passwordAgain: string;
    userModel: UserEditerDTO = <UserEditerDTO>{};
    personModel: PersonDTO = <PersonDTO>{};
    maxDate = new Date();
    minDate = new Date(1919, 0, 1);

    isValid = this.validation();
    isValidPassword = true;

    constructor(private userService: UserService, private toastr: ToastrService, private router: Router) {
    }

    saveFunction() {
        if (this.validation()) {
            this.save();
        } else {
            this.toastr.error('Validációs hiba történt!');
        }
    }

    private save() {
        this.userModel.person = this.personModel;
        this.userService.create(this.userModel).subscribe(user => {
            if (user) {
                this.toastr.success('Sikeres!', 'Regsiztráció sikeres volt!');
                this.router.navigateByUrl('/login');
            } else {
                this.toastr.error('Sikertelen!', 'Regsiztráció sikertelen volt!');
            }
        });
    }

    public password() {
        if (this.userModel.password === this.passwordAgain) {
            this.isValidPassword = true;
            return true;
        }
        this.isValidPassword = false;
        return false;
    }

    public validation() {
        if ( !this.userModel.userName || !this.userModel.password || !this.passwordAgain
            || !this.personModel.email || !this.personModel.firstName || !this.personModel.lastName) {
                this.isValid = false;
                return false;
            }
        return this.isValid = this.password();
    }
}
