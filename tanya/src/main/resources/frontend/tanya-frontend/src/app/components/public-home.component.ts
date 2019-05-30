import { OnInit, Component } from '@angular/core';

@Component({
    selector: 'app-public-home-component',
    templateUrl: './public-home.component.html',
})
export class PublicHomeComponent implements OnInit {

    ngOnInit() {
        console.log('PublicHomeComponent');
    }
}
