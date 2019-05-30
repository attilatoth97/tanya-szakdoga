import { OnInit, Component } from '@angular/core';
import { TaskService } from 'src/app/service/task.service';
import { TaskEditerDTO } from 'src/app/model/task.editer.modal';
import { UserMiniDTO } from 'src/app/model/user.mini.dto';

@Component({
    selector: 'app-task-create-component',
    templateUrl: './task-create.component.html',
    styleUrls: ['./task-create.component.css'],
    providers: [TaskService]

})

export class TaskCreateComponent implements OnInit {

    taskModel: TaskEditerDTO = <TaskEditerDTO>{};
    groupMambers: UserMiniDTO[] = [];
    constructor(taskService: TaskService) {}

    ngOnInit() {

    }

}
