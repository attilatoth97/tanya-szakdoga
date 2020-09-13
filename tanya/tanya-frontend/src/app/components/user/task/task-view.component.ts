import { OnInit, Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TaskDTO } from 'src/app/model/task.dto.modal';
import { TaskService } from 'src/app/service/task.service';

@Component({
    selector: 'app-task-view-component',
    templateUrl: './task-view.component.html',
    styleUrls: ['./task-view.component.css'],
    providers: [TaskService]

})
export class TaskViewComponent implements OnInit {

    currentTask = {} as TaskDTO;

    constructor(private taskService: TaskService, private activeRoute: ActivatedRoute) { }

    ngOnInit(): void {
        const currentTaskId = parseInt(this.activeRoute.snapshot.params['id'], 10);
        this.taskService.getTask(currentTaskId).subscribe(task => {
            this.currentTask = task;
        });

    }


}
