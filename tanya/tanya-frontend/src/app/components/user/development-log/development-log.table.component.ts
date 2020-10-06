import { Component, Input } from '@angular/core';
import { DevelopmentLogDTO } from 'src/app/model/development-log.dto.model';

@Component({
    selector: 'app-development-log-table-component',
    templateUrl: './development-log-table.component.html'
})
export class DevelopmentLogTableComponent {

    @Input() developmentDTOs = [] as DevelopmentLogDTO[];

    public displayedColumns: string[] = ['projectName', 'userName', 'taskName', 'day', 'developedHours', 'show'];
}
