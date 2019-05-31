import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { LoginDTO } from 'src/app/model/login.dto.modal';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { frontendUrl } from 'src/app/app.constant';

@Component({
    selector: 'app-login-component',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    providers: [AuthService]

})
export class LoginComponent  {

    private login: LoginDTO = <LoginDTO>{};
    public userName: string;
    public password: string;
    public url: string;

    constructor(private authService: AuthService, private route: Router, private toast: ToastrService ) {}

    getTokenFromBackeEnd() {
       if (this.password && this.userName) {
         this.goLoginAndSavedTheToken();
       } else {
        this.toast.error('Felhasználó név és jelszó megadása kötelező');
       }

      }

      private goLoginAndSavedTheToken() {
        this.login.password = this.password;
        this.login.userName = this.userName;
        this.authService.getToken(this.login).subscribe((token) => {
          window.localStorage.setItem('id_token', token[0]);
          this.toast.success('Sikeres bejelentkezés');
          window.location.replace( frontendUrl);
        }
        );
      }

}
