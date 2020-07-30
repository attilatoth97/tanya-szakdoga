import { Component, OnInit } from '@angular/core';
import { ProjectMapDTO } from 'src/app/model/project.map.dto.modal';
import { ProjectService } from 'src/app/service/project.service';
import { TaskService } from 'src/app/service/task.service';
import { TaskMiniDTO } from 'src/app/model/task.mini.dto';

@Component({
    selector: 'app-current-user-task-component',
    templateUrl: './current-user-task-component.html',
    styleUrls: ['./current-user-task-component.scss'],
    providers: [ProjectService, TaskService]
})
export class CurrentUserTaskComponent implements OnInit {

    projectMaps = [] as ProjectMapDTO[];
    selectedProjectId: number;

    ownTask = [] as TaskMiniDTO[];
    attendantTask = [] as TaskMiniDTO[];

    constructor(public projectService: ProjectService, public taskService: TaskService) { }

    ngOnInit(): void {
        this.projectService.getProjectMiniDTOOwn().subscribe(projects => {
            this.projectMaps = projects;
        });
        this.taskService.getAllOwnCreatedTask().subscribe(tasks => {
            this.ownTask = tasks;
        });

        this.taskService.getAllOwnResponsibledTask().subscribe(tasks => {
            this.attendantTask = tasks;
        });
    }

    createTask(): void {

    }

    navigate(id: number): void {

    }
}
