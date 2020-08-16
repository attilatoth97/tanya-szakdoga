import { OnInit, Component } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { UserEditerDTO } from 'src/app/model/user.editer.dto.modal';
import { PersonDTO } from 'src/app/model/person.dto.modal';
import { ToastrService } from 'ngx-toastr';

@Component({
    selector: 'app-person-details-component',
    templateUrl: './person-details.component.html',
    styleUrls: ['./person-details.component.css'],
    providers: [UserService]

})
export class PersonDetailsComponent implements OnInit {

    passwordAgain: string;
    userModel: UserEditerDTO = <UserEditerDTO>{};
    personModel: PersonDTO = <PersonDTO>{};
    maxDate = new Date();
    minDate = new Date(1919, 0, 1);

    isValid = this.validation();
    isValidPassword = true;

    constructor(private userService: UserService, private toastr: ToastrService) {
    }

    ngOnInit(): void {
        this.initLoggedUserModel();
    }

    initLoggedUserModel() {
        this.userService.getLoggedUser().subscribe(logged => {
            this.userModel = logged;
            this.personModel = logged.person;
        });
    }

    saveFunction() {
        if (this.validation()) {
            this.save();
        } else {
            this.toastr.error('Validációs hiba történt!');
        }
    }

    save() {
        this.userModel.person = this.personModel;
        this.userService.update(this.userModel).subscribe(user => {
            if (user) {
                this.toastr.success('Sikeres!', 'Mentés sikeres volt!!');
            } else {
                this.toastr.error('Sikertelen!', 'Mentés sikertelen volt!');
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
        if (!this.userModel.userName || !this.userModel.password || !this.passwordAgain
            || !this.personModel.email || !this.personModel.firstName || !this.personModel.lastName) {
            this.isValid = false;
            return false;
        }
        return this.isValid = this.password();
    }
}
