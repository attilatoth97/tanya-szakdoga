import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { DevelopmentLogCreateDTO } from 'src/app/model/development-log-create-dto.model';
import { DevelopmentLogDTO } from 'src/app/model/development-log.dto.model';
import { ProjectMapDTO } from 'src/app/model/project.map.dto.modal';
import { TaskMiniDTO } from 'src/app/model/task.mini.dto';
import { DevelopmentLogService } from 'src/app/service/development-log.service';
import { ProjectService } from 'src/app/service/project.service';
import { TaskService } from 'src/app/service/task.service';

@Component({
    selector: 'app-development-log-page-component',
    templateUrl: './development-log-page.component.html',
    providers: [DevelopmentLogService, ProjectService]
})
export class DevelopmentLogPageComponent implements OnInit {

    public developmentDTOs = [] as DevelopmentLogDTO[];
    public projectMaps = [] as ProjectMapDTO[];
    public selectedProjectId: number;
    public buttonDisable = false;
    public readonly displayedColumns: string[] = ['day', 'taskName', 'projectName', 'description', 'developedHours', 'show'];

    constructor(private developmentLogService: DevelopmentLogService,
        public projectService: ProjectService,
        private dialog: MatDialog,
        private router: Router) { }

    ngOnInit(): void {
        this.initData();
    }

    initData(): void {
        this.initDevelopmentLog();
        this.projectService.getProjectMiniDTOOwn().subscribe(projects => {
            this.projectMaps = projects;
        });
    }

    initDevelopmentLog(): void {
        this.developmentLogService.findAllByCurrentUserId().subscribe(logs => {
            this.developmentDTOs = logs;
        });
    }

    navigateToTaskView(id: number) {
        this.router.navigateByUrl('/task-view/' + id);
    }

    delete(id: number) {
        this.developmentLogService.delete(id).subscribe(() => {
            this.initDevelopmentLog();
        });
    }

    create() {
        if (this.selectedProjectId) {
            const dialogRef = this.dialog.open(DevelopmentLogDialog, {
                width: '500px',
                //height: '200px'
                data: { projectId: this.selectedProjectId }
            });

            dialogRef.afterClosed().subscribe(result => {
                this.initDevelopmentLog();
            });
        }
    }

    update(id: number) {
        const dialogRef = this.dialog.open(DevelopmentLogDialog, {
            width: '500px',
            //height: '200px'
            data: { developmentLogId: id }
        });

        dialogRef.afterClosed().subscribe(result => {
            this.initDevelopmentLog();
        });
    }
}

@Component({
    selector: 'app-development-log-dialog-component',
    templateUrl: './development-log-dialog.component.html',
    providers: [TaskService, DevelopmentLogService]
})
// tslint:disable-next-line:component-class-suffix
export class DevelopmentLogDialog implements OnInit {

    model: DevelopmentLogCreateDTO = <DevelopmentLogCreateDTO>{};
    tasks: TaskMiniDTO[] = [];


    constructor(
        public dialogRef: MatDialogRef<DevelopmentLogDialog>,
        private toast: ToastrService,
        private taskService: TaskService,
        private developmentLogService: DevelopmentLogService,
        @Inject(MAT_DIALOG_DATA) public data,
    ) { }

    ngOnInit(): void {
        if (this.data.developmentLogId) {
            this.initDevelopmentLog();
        }
        this.initTask();
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    save() {
        this.model.projectId = this.data.projectId;
        const callService = this.data.developmentLogId ? this.update() : this.create();
        callService.subscribe(developmentLog => {
            if (developmentLog) {
                this.toast.success('Sikeres mentés');
                this.dialogRef.close();
            } else {
                this.toast.error('Sikertelen mentés');
            }
        });
    }

    private create(): Observable<DevelopmentLogDTO> {
        this.model.projectId = this.data.projectId;
        return this.developmentLogService.create(this.model);
    }

    private update(): Observable<DevelopmentLogDTO> {
        return this.developmentLogService.update(this.data.developmentLogId, this.model);
    }

    private initDevelopmentLog(): void {
        this.developmentLogService.getDevelopmentLog(this.data.developmentLogId).subscribe(developmentLog => {
            this.model = developmentLog as DevelopmentLogCreateDTO;
        });
    }

    private initTask(): void {
        this.taskService.getTasksByProjectId(this.data.projectId).subscribe(tasks => {
            this.tasks = tasks;
        });
    }
}
