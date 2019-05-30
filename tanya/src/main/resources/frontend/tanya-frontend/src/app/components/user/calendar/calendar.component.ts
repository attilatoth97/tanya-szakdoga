import { Component, OnInit } from '@angular/core';
import { FullCalendarOptions, EventObject } from 'ngx-fullcalendar';

@Component({
    selector: 'app-calendar-component',
    templateUrl: './calendar.component.html',
    providers: []

})
export class CalendarComponent implements OnInit {

    options: FullCalendarOptions;
    events: EventObject[];

    ngOnInit(): void {
        this.options = {
            defaultDate: '2018-07-26',
            editable: true,
            };

          this.events = [
            { id: 'a', title: 'My Birthday', allDay: true },
            { id: 'b', title: 'Friends coming round', start: '2018-07-26T18:00:00', end: '2018-07-26T23:00:00' }];
    }

}
