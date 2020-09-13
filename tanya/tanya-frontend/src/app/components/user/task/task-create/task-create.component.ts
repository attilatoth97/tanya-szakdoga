import { OnInit, Component } from '@angular/core';
import { TaskService } from 'src/app/service/task.service';
import { TaskEditerDTO } from 'src/app/model/task.editer.modal';
import { UserMiniDTO } from 'src/app/model/user.mini.dto';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from 'src/app/service/project.service';
import { Observable, forkJoin } from 'rxjs';
import { SprintService } from 'src/app/service/sprint.service';
import { SprintMapDTO } from 'src/app/model/sprint.map.dto';
import { ToastrService } from 'ngx-toastr';
import { IssueType } from 'src/app/model/enum/issue-type.enum';
import { IssueStatus } from 'src/app/model/enum/issue-status.enum';

@Component({
    selector: 'app-task-create-component',
    templateUrl: './task-create.component.html',
    styleUrls: ['./task-create.component.css'],
    providers: [TaskService, ProjectService, SprintService]

})
export class TaskCreateComponent implements OnInit {

    taskModel: TaskEditerDTO = <TaskEditerDTO>{};
    groupMambers: UserMiniDTO[] = [];
    projectId: number;
    sprints: SprintMapDTO[] = [];
    issueTypes: Array<String> = [];
    issueStatus: Array<String> = [];

    constructor(private taskService: TaskService, private projectService: ProjectService, private sprintService: SprintService,
        private activeRoute: ActivatedRoute, private router: Router, private toast: ToastrService) { }

    ngOnInit() {
        this.initModel();
        this.initEnum();
    }

    private initModel() {
        this.projectId = parseInt(this.activeRoute.snapshot.params['id'], 10);
        this.taskModel.projectId = this.projectId;
        forkJoin(
            this.projectService.getMiniUserInGroup(this.projectId),
            this.sprintService.getTheProjectsSprints(this.projectId)
        ).subscribe(([users, sprints]) => {
            this.groupMambers = users;
            this.sprints = sprints;
        }, () => {
        });
    }

    backnavigate() {
        this.router.navigateByUrl('/sprint-task-list/' + this.projectId);
    }

    saveTask() {
        this.taskService.create(this.taskModel).subscribe(task => {
            if (task) {
                this.toast.success('Sikeres mentés');
                this.backnavigate();
            } else {
                this.toast.error('Sikertelen mentés');
            }
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

}
