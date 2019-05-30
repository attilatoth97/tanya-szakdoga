import { OnInit, Component } from '@angular/core';
import { initChangeDetectorIfExisting } from '@angular/core/src/render3/instructions';

@Component({
    selector: 'app-start-page-component',
    templateUrl: './start-page.component.html',
    styleUrls: ['./start-page.component.css']
})
export class StartPageComponent implements OnInit {

    ngOnInit() {
    }
}
