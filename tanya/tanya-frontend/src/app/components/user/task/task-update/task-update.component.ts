import { OnInit, Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TaskDTO } from 'src/app/model/task.dto.modal';
import { TaskEditerDTO } from 'src/app/model/task.editer.modal';
import { SprintService } from 'src/app/service/sprint.service';
import { TaskService } from 'src/app/service/task.service';

@Component({
    selector: 'app-task-update-component',
    templateUrl: './task-update.component.html',
    styleUrls: ['./task-update.component.css'],
    providers: [TaskService, SprintService]
})
export class TaskUpdateComponent implements OnInit {

    taskModel: TaskEditerDTO = <TaskEditerDTO>{};
    currentTask: TaskDTO = <TaskDTO>{};

    constructor(private taskService: TaskService, private sprintService: SprintService,
        private activeRoute: ActivatedRoute, private router: Router, private toast: ToastrService) { }

    ngOnInit(): void {
        const currentTaskId = parseInt(this.activeRoute.snapshot.params['id'], 10);
        this.taskService.getTask(currentTaskId).subscribe(task => {
            this.currentTask = task;
        });
    }

    update() { }

}
