import { Component, OnInit, Inject } from '@angular/core';
import { SprintService } from 'src/app/service/sprint.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SprintDTO } from 'src/app/model/sprint.dto';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { SprintEditorDTO } from 'src/app/model/sprint.editor.dto';
import { ToastrService } from 'ngx-toastr';

export interface PeriodicElement {
    name: string;
    index: number;
    type: string;
  }

  const ELEMENT_DATA: PeriodicElement[] = [
    {index: 1, name: 'Design-startpage', type: 'Feature'},
    {index: 2, name: 'Bugfix-table', type: 'Bugfix'},
    {index: 3, name: 'Projekt bemutato oldal',  type: 'Feature'},
    {index: 4, name: 'Sprint ek listázása', type: 'Feature'},
  ];

@Component({
    selector: 'app-sprint-task-component',
    templateUrl: './sprint-task.component.html',
    styleUrls: ['./sprint-task.component.css'],
    providers: [SprintService]

})

export class SprintTaskComponent implements OnInit {

    displayedColumns: string[] = ['index', 'name', 'type'];
    sprints: SprintDTO[];
    buttonDisable = false;
    projectId: number;
    constructor(private sprintService: SprintService,
                private activeRoute: ActivatedRoute,
                private route: Router,
                private dialog: MatDialog) {}
    ngOnInit(): void {
        this.initSprintAndTask();
    }

    initSprintAndTask() {
        this.projectId = parseInt(this.activeRoute.snapshot.params['id'], 10);
        this.sprintService.getTheProjectsSprints(this.projectId).subscribe(sprints => {
            this.sprints = sprints;
        }, error => {});
    }

    navigate(id: number) {
        console.log('test');
        this.route.navigateByUrl('/sprint-task-list/' + id);
    }

    openSprintDialog() {
        this.buttonDisable = true;
        const dialogRef = this.dialog.open(SprintDialog, {
            width: '500px',
            height: '350px',
            data:  this.projectId,
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

    sprintModel: SprintEditorDTO = <SprintEditorDTO>{};
    end = new Date();
    start = new Date();
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
            console.log('save-dialog');
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
