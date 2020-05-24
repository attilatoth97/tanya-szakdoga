import { OnInit, Component } from '@angular/core';
import { TaskService } from 'src/app/service/task.service';

@Component({
    selector: 'app-task-update-component',
    templateUrl: './task-update.component.html',
    styleUrls: ['./task-update.component.css'],
    providers: [TaskService]

})

export class TaskUpdateComponent implements OnInit {

    ngOnInit(): void {

    }

    update() { }

}
