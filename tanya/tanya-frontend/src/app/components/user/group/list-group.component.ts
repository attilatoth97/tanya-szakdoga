import { Component, OnInit } from '@angular/core';
import { GroupService } from 'src/app/service/group.service';
import { GroupDTO } from 'src/app/model/group.dto.modal';
import { MatDialog, MatDialogRef } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
    selector: 'app-list-group-component',
    templateUrl: './list-group.component.html',
    styleUrls: ['./list-group.component.css'],
    providers: [GroupService]

})
export class ListGroupComponent implements OnInit {

    public ownGroup: GroupDTO[] = [];
    public attendantGroup: GroupDTO[] = [];
    public init = false;
    public buttonDisable = false;

    constructor(private groupService: GroupService, private dialog: MatDialog, private route: Router) { }

    ngOnInit(): void {
        this.initGroups().then(() => {
            this.init = true;
        });
    }

    initOwnGroup() {
        this.groupService.getUserCreatedGroups().subscribe(groups => {
            this.ownGroup = groups;
        });
    }

    initAttendantGroup() {
        this.groupService.getGroupsWhereUserAttendant().subscribe(groups => {
            this.attendantGroup = groups;
        });
    }

    initGroups(): Promise<void> {
        return new Promise((resolve, reject) => {
            this.initOwnGroup();
            this.initAttendantGroup();
            resolve();
        });
    }

    navigate(id: number) {
        console.log('test');
        this.route.navigateByUrl('/project-list/' + id);
    }

    createOrUpdateGroup() {
        this.buttonDisable = true;
        const dialogRef = this.dialog.open(CreateOrUpdateGroupDialog, {
            width: '500px',
            height: '200px'
        });

        dialogRef.afterClosed().subscribe(result => {
            this.buttonDisable = false;
            this.initOwnGroup();
        });
    }
}

@Component({
    selector: 'app-list-group-dialog-component',
    templateUrl: './list-group-dialog.component.html',
    providers: [GroupService]
})
// tslint:disable-next-line:component-class-suffix
export class CreateOrUpdateGroupDialog {

    groupModel: GroupDTO = <GroupDTO>{};
    isValid = false;
    constructor(
        public dialogRef: MatDialogRef<CreateOrUpdateGroupDialog>,
        private groupService: GroupService,
        private toast: ToastrService) {
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    save() {
        this.groupService.create(this.groupModel).subscribe(group => {
            if (group) {
                this.dialogRef.close();
            }
        });
    }

    valid() {
        if (this.groupModel.groupName) {
            this.isValid = true;
        } else {
            this.isValid = false;

        }
    }
}

