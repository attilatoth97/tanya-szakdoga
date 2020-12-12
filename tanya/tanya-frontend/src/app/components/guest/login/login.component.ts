import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { LoginDTO } from 'src/app/model/login.dto.modal';
import { ToastrService } from 'ngx-toastr';
import { AuthHelperService } from 'src/app/auth-helper.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthService]

})
export class LoginComponent {

  private login: LoginDTO = <LoginDTO>{};
  public userName: string;
  public password: string;
  public url: string;

  constructor(
    private authService: AuthService,
    private authHelperService: AuthHelperService,
    private toast: ToastrService) { }

  getTokenFromBackeEnd() {
    if (this.password && this.userName) {
      this.goLoginAndSavedTheToken();
    } else {
      this.toast.error('Felhasználónév és jelszó megadása kötelező');
    }

  }

  private goLoginAndSavedTheToken() {
    this.login.password = this.password;
    this.login.userName = this.userName;
    this.authService.getToken(this.login).subscribe((token) => {
      this.authHelperService.login(token);
    }
    );
  }

}
