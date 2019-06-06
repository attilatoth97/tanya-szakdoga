import { Component, OnInit } from '@angular/core';
import { FullCalendarOptions, EventObject } from 'ngx-fullcalendar';
import { MatDialog, MatDialogRef} from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { CalendarDTO } from 'src/app/model/calendar.dto.modal';
import { CalendarEditerDTO } from 'src/app/model/calendar.editer.dto.modal';

@Component({
    selector: 'app-calendar-component',
    templateUrl: './calendar.component.html',
    providers: []

})
export class CalendarComponent implements OnInit {

    options: FullCalendarOptions;
    calendar: CalendarDTO[] =  [];
    events: EventObject[];
    buttonDisable = false;

    constructor(private dialog: MatDialog) {}

    ngOnInit(): void {
        this.options = {
            defaultDate: new Date,
            editable: false,
            };
          this.events = [
            { id: 'b', title: 'Friends coming round', allDay: true, start: new Date },
            { id: 'c', title: 'Winter is Coming', allDay: false, start: new Date, end: new Date(2019, 6, 10, 10, 14, 20) }];

    }

    eventClick(event: any) {
        // szerkesztéshez id
        console.log(event.event.id);
        console.log('Click');
    }

    calendarMaptoEventObject() {
        let event = null;
        this.calendar.forEach(e => {
            event = <EventObject>{
                id: e.id, title: e.title, start: e.date, allDay: true
            };
            this.events.push(event);
        });
    }

    openCalendarDialog() {
        this.buttonDisable = true;
        const dialogRef = this.dialog.open(CalendarDialog, {
            width: '500px',
            height: '350px',
          });

          dialogRef.afterClosed().subscribe(result => {
            this.buttonDisable = false;
            // this.initSprintAndTask();
          });
    }

}
@Component({
    selector: 'app-calendar-dialog-component',
    templateUrl: './calendar-dialog.component.html',
    providers: []
  })
// tslint:disable-next-line:component-class-suffix
export class CalendarDialog {

    calendar: CalendarEditerDTO = <CalendarEditerDTO>{};
    end = new Date();
    start = new Date();
    minDate = new Date(2000, 0, 1);
    maxDate = new Date(2030, 0, 1);

    constructor( public dialogRef: MatDialogRef<CalendarDialog>,
        private toast: ToastrService,
        ) {}

        onNoClick(): void {
        this.dialogRef.close();
        }

        save() {
           /* this.sprintModel.projectId = this.data;
            this.sprintService.create(this.sprintModel).subscribe(sprint => {
                if (sprint) {
                    this.toast.success('Sikeres mentés');
                    this.dialogRef.close();
                } else {
                    this.toast.error('Sikertelen mentés');
                }
            });*/
        }
  }
