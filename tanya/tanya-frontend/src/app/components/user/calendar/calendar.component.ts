import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { CalendarDTO } from 'src/app/model/calendar.dto.modal';
import { CalendarEditerDTO } from 'src/app/model/calendar.editer.dto.modal';
import { CalendarService } from 'src/app/service/calendar.serivce';
import { ProjectMapDTO } from 'src/app/model/project.map.dto.modal';
import { ProjectService } from 'src/app/service/project.service';
import dayGridPlugin from '@fullcalendar/daygrid';

@Component({
    selector: 'app-calendar-component',
    templateUrl: './calendar.component.html',
    providers: [CalendarService, ProjectService]

})
export class CalendarComponent implements OnInit {


    calendarPlugins = [dayGridPlugin];

    options;
    calendar: CalendarDTO[] = [];
    calendarEvents;
    buttonDisable = false;
    calendarId: number;
    projectMap: ProjectMapDTO[] = [];
    selectProjectId: number;
    constructor(private dialog: MatDialog,
        private calendarSerivce: CalendarService, private projectService: ProjectService) { }

    ngOnInit(): void {
        this.initProjectSelect();
        this.initCalendar();
    }

    private initProjectSelect() {
        this.projectService.getProjectMiniDTOOwn().subscribe(e => {
            this.projectMap = e;
        });
    }

    onChange() {
        this.initCalendar();
    }

    initCalendar() {
        this.calendar = [];
        this.calendarEvents = [];
        if (this.selectProjectId) {
            this.calendarSerivce.getCalendarDates(this.selectProjectId).subscribe(e => {
                this.calendar = e;
                this.calendarMaptoEventObject();
            });
        }
    }

    eventClick(event: any) {
        this.calendarId = event.event.id;
        this.openCalendarDialog();
    }

    private calendarMaptoEventObject() {
        let event = null;
        this.calendar.forEach(e => {
            event = {
                id: e.id, title: e.title, date: e.date
            };
            this.calendarEvents.push(event);
        });
        console.log(this.calendarEvents);
    }

    openCalendarDialogEvent() {
        this.calendarId = null;
        this.openCalendarDialog();
    }

    openCalendarDialog() {
        this.buttonDisable = true;
        const dialogRef = this.dialog.open(CalendarDialog, {
            width: '500px',
            height: '425px',
            data: this.calendarId,
        });

        dialogRef.afterClosed().subscribe(result => {
            this.buttonDisable = false;
            this.initCalendar();
        });
    }

}
@Component({
    selector: 'app-calendar-dialog-component',
    templateUrl: './calendar-dialog.component.html',
    providers: [CalendarService, ProjectService]
})
// tslint:disable-next-line:component-class-suffix
export class CalendarDialog implements OnInit {

    calendar: CalendarEditerDTO = <CalendarEditerDTO>{};
    end = new Date();
    start = new Date();
    minDate = new Date(2000, 0, 1);
    maxDate = new Date(2030, 0, 1);
    projectMap: ProjectMapDTO[] = [];


    constructor(
        public dialogRef: MatDialogRef<CalendarDialog>,
        private toast: ToastrService,
        private calendarService: CalendarService,
        private projectService: ProjectService,
        @Inject(MAT_DIALOG_DATA) public data: number,
    ) { }

    ngOnInit(): void {
        this.initProjectSelect();
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    save() {
        this.calendar.id = this.data;
        const callService = this.calendar.id ?
            this.calendarService.update(this.calendar) : this.calendarService.create(this.calendar);
        callService.subscribe(sprint => {
            if (sprint) {
                this.toast.success('Sikeres mentés');
                this.dialogRef.close();
            } else {
                this.toast.error('Sikertelen mentés');
            }
        });
    }

    private initProjectSelect() {
        this.projectService.getProjectMiniDTOOwn().subscribe(e => {
            this.projectMap = e;
        });
    }
}
