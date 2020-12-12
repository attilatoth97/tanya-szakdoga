import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { SprintService } from 'src/app/service/sprint.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SprintDTO } from 'src/app/model/sprint.dto';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { SprintEditorDTO } from 'src/app/model/sprint.editor.dto';
import { ToastrService } from 'ngx-toastr';
import { TaskService } from 'src/app/service/task.service';
import { NgModel } from '@angular/forms';

@Component({
    selector: 'app-sprint-task-component',
    templateUrl: './sprint-task.component.html',
    styleUrls: ['./sprint-task.component.css'],
    providers: [SprintService, TaskService]
})
export class SprintTaskComponent implements OnInit {

    displayedColumns: string[] = ['issueName', 'createUserName', 'responsibleUserName', 'issueStatus', 'issueType', 'show'];
    sprints: SprintDTO[];
    buttonDisable = false;
    projectId: number;
    constructor(private sprintService: SprintService,
        private taskService: TaskService,
        private activeRoute: ActivatedRoute,
        private route: Router,
        private dialog: MatDialog) { }

    ngOnInit(): void {
        this.projectId = parseInt(this.activeRoute.snapshot.params['id'], 10);
        this.initSprintAndTask();
    }

    initSprintAndTask() {
        this.sprintService.getTheProjectsSprints(this.projectId).subscribe(sprints => {
            this.sprints = sprints;
        }, error => { });
    }

    deleteTask(id: number) {
        this.taskService.delete(id).subscribe(() => {
            this.initSprintAndTask();
        });
    }

    navigateToView(id: number) {
        this.route.navigateByUrl('/task-view/' + id);
    }

    navigateToModify(id: number) {
        this.route.navigateByUrl('/task-update/' + id);
    }

    openSprintDialog() {
        this.buttonDisable = true;
        const dialogRef = this.dialog.open(SprintDialog, {
            width: '500px',
            //height: auto,
            data: this.projectId,
        });

        dialogRef.afterClosed().subscribe(result => {
            this.buttonDisable = false;
            this.initSprintAndTask();
        });
    }

    navigateCreateTask() {
        this.route.navigateByUrl('/task-create/' + this.projectId);
    }

}
@Component({
    selector: 'app-sprint-dialog-component',
    templateUrl: './sprint-dialog.component.html',
    providers: [SprintService]
})
// tslint:disable-next-line:component-class-suffix
export class SprintDialog {

    @ViewChild('sprintForm', { static: false }) sprintForm: NgModel;

    sprintModel: SprintEditorDTO = <SprintEditorDTO>{};
    end = new Date();
    start = new Date();
    minDate = new Date(2000, 0, 1);
    maxDate = new Date(2030, 0, 1);

    constructor(
        public dialogRef: MatDialogRef<SprintDialog>,
        private sprintService: SprintService,
        private toast: ToastrService,
        @Inject(MAT_DIALOG_DATA) public data: number) {
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    save() {
        this.sprintModel.projectId = this.data;
        this.sprintService.create(this.sprintModel).subscribe(sprint => {
            if (sprint) {
                this.toast.success('Sikeres mentés');
                this.dialogRef.close();
            } else {
                this.toast.error('Sikertelen mentés');
            }
        });
    }
}
