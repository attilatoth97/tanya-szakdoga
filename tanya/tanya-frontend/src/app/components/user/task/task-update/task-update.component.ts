import { OnInit, Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { forkJoin, Observable } from 'rxjs';
import { SprintMapDTO } from 'src/app/model/sprint.map.dto';
import { TaskEditerDTO } from 'src/app/model/task.editer.modal';
import { UserMiniDTO } from 'src/app/model/user.mini.dto';
import { ProjectService } from 'src/app/service/project.service';
import { SprintService } from 'src/app/service/sprint.service';
import { TaskService } from 'src/app/service/task.service';
import { IssueType } from 'src/app/model/enum/issue-type.enum';
import { IssueStatus } from 'src/app/model/enum/issue-status.enum';
import { TaskDTO } from 'src/app/model/task.dto.modal';
@Component({
    selector: 'app-task-update-component',
    templateUrl: './task-update.component.html',
    styleUrls: ['./task-update.component.css'],
    providers: [TaskService, SprintService, ProjectService]
})
export class TaskUpdateComponent implements OnInit {

    taskModel: TaskEditerDTO = <TaskEditerDTO>{};
    groupMembers: UserMiniDTO[] = [];
    sprints: SprintMapDTO[] = [];
    issueTypes: Array<String> = [];
    issueStatus: Array<String> = [];
    currentProjectName: string;

    constructor(private taskService: TaskService, private sprintService: SprintService,
        private projectService: ProjectService,
        private activeRoute: ActivatedRoute, private router: Router, private toast: ToastrService) { }

    ngOnInit(): void {
        this.initData();
        this.initEnum();
    }

    initData() {
        const currentTaskId = parseInt(this.activeRoute.snapshot.params['id'], 10);
        this.taskService.getTask(currentTaskId).subscribe(task => {
            this.currentProjectName = task.projectName;
            this.taskModel = task as TaskEditerDTO;
            this.getMetaData(task.projectId).subscribe(([sprints, members]) => {
                this.sprints = sprints;
                this.groupMembers = members;
            });
        });
    }

    initEnum() {
        Object.keys(IssueType).forEach(e => {
            this.issueTypes.push(e);
        });

        Object.keys(IssueStatus).forEach(e => {
            this.issueStatus.push(e);
        });
    }

    getMetaData(projectId: number): Observable<any> {
        const sprintsObs = this.sprintService.getTheProjectsSprints(projectId);
        const membersObs = this.projectService.getMiniUserInGroup(projectId);
        return forkJoin([sprintsObs, membersObs]);
    }

    private buildTaskEditerDTO(task: TaskDTO): TaskEditerDTO {
        this.currentProjectName = task.projectName;
        return {
            issueName: task.issueName,
            description: task.description,
            responsibleUserId: task.responsibleUserId,
            issueStatus: task.issueStatus,
            issueType: task.issueType,
            sprintId: task.sprintId,
            projectId: task.projectId,
            estimatedTime: task.estimatedTime
        } as TaskEditerDTO;
    }

    updateTask() { }

}
