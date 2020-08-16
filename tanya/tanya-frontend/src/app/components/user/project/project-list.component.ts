import { OnInit, Component, Inject } from '@angular/core';
import { ProjectService } from 'src/app/service/project.service';
import { ProjectDTO } from 'src/app/model/project.dto.modal';
import { GroupService } from 'src/app/service/group.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectEditerDTO } from 'src/app/model/project.editer.dto.modal';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-project-list-component',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css'],
  providers: [ProjectService, GroupService]

})
export class ProjectListComponent implements OnInit {

  displayedColumns: string[] = ['projectName', 'createrUsername', 'sprintNumber', 'taskNumber', 'show'];
  public projects: ProjectDTO[] = [];
  public fullNames: string[] = [];
  groupId: number;
  public buttonDisable = false;

  constructor(private projectService: ProjectService,
    private groupService: GroupService,
    private route: Router,
    private activeRoute: ActivatedRoute,
    private dialog: MatDialog) { }
  ngOnInit() {
    this.groupId = parseInt(this.activeRoute.snapshot.params['id'], 10);
    this.initProjects();
    this.initUserFullNameInGroup();
  }

  initProjects() {
    this.projectService.getProjectsInGroup(this.groupId).subscribe(projects => {
      this.projects = projects;
    });
  }

  initUserFullNameInGroup() {
    this.groupService.getUserNameinGroup(this.groupId).subscribe(fullnames => {
      this.fullNames = fullnames;
    });
  }

  navigate(id: number) {
    this.route.navigateByUrl('/sprint-task-list/' + id);
  }

  openProjectDialog() {
    this.buttonDisable = true;
    const dialogRef = this.dialog.open(ProjectDialog, {
      width: '500px',
      //height: '350px',
      data: this.groupId,
    });

    dialogRef.afterClosed().subscribe(result => {
      this.initProjects();
      this.buttonDisable = false;
    });
  }

  openUserAddDialog() {
    this.buttonDisable = true;
    const dialogRef = this.dialog.open(UserAddDialog, {
      data: this.groupId,
      width: '500px',
      //height: '350px'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.initUserFullNameInGroup();
      this.buttonDisable = false;
    });
  }
}
@Component({
  selector: 'app-project-dialog-component',
  templateUrl: './project-dialog.component.html',
  providers: [ProjectService]
})
// tslint:disable-next-line:component-class-suffix
export class ProjectDialog {

  projectModel: ProjectEditerDTO = <ProjectEditerDTO>{};

  constructor(
    public dialogRef: MatDialogRef<ProjectDialog>,
    private projectService: ProjectService,
    private toast: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: number) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  save() {
    this.projectModel.groupId = this.data;
    this.projectService.create(this.projectModel).subscribe(project => {
      if (project) {
        this.toast.success('Sikeres mentés');
        this.dialogRef.close();
      } else {
        this.toast.error('Sikertelen mentés');
      }
    });
  }
}

@Component({
  selector: 'app-user-add-dialog-component',
  templateUrl: './user-add-dialog.component.html',
  providers: [GroupService]
})
// tslint:disable-next-line:component-class-suffix
export class UserAddDialog {

  username: string;

  constructor(
    public dialogRef: MatDialogRef<UserAddDialog>,
    private groupService: GroupService,
    private toast: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: number) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  save() {
    this.groupService.addUserForGroup(this.data.valueOf(), this.username).subscribe(() => {
      this.toast.success('Sikeres mentés');
      this.dialogRef.close();
    }, error => {
      this.toast.error('Sikertelen mentés');
    });
  }
}
