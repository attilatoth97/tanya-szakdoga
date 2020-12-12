import { OnInit, Component } from '@angular/core';
import * as Chartist from 'chartist';
import { forkJoin } from 'rxjs';
import { StatisticsService } from 'src/app/service/statistics.service';

@Component({
    selector: 'app-start-page-component',
    templateUrl: './start-page.component.html',
    styleUrls: ['./start-page.component.scss'],
    providers: [StatisticsService]
})
export class StartPageComponent implements OnInit {

    groupNumber: number;
    projectNumber: number;
    createdTaskNummber: number;
    completedTaskNumber: number;
    developmentLogSum: number;
    constructor(private statisticsService: StatisticsService) { }

    private startAnimationForLineChart(chart) {
        let seq: any, delays: any, durations: any;
        seq = 0;
        delays = 80;
        durations = 500;

        chart.on('draw', function (data) {
            if (data.type === 'line' || data.type === 'area') {
                data.element.animate({
                    d: {
                        begin: 600,
                        dur: 700,
                        from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
                        to: data.path.clone().stringify(),
                        easing: Chartist.Svg.Easing.easeOutQuint
                    }
                });
            } else if (data.type === 'point') {
                seq++;
                data.element.animate({
                    opacity: {
                        begin: seq * delays,
                        dur: durations,
                        from: 0,
                        to: 1,
                        easing: 'ease'
                    }
                });
            }
        });

        seq = 0;
    }

    private startAnimationForBarChart(chart) {
        let seq2: any, delays2: any, durations2: any;

        seq2 = 0;
        delays2 = 80;
        durations2 = 500;
        chart.on('draw', function (data) {
            if (data.type === 'bar') {
                seq2++;
                data.element.animate({
                    opacity: {
                        begin: seq2 * delays2,
                        dur: durations2,
                        from: 0,
                        to: 1,
                        easing: 'ease'
                    }
                });
            }
        });

        seq2 = 0;
    }

    ngOnInit(): void {
        this.initNumberStatistics();
        this.initDevelopmentLogByGroup();
        this.initDevelopmentLog();
    }

    private initNumberStatistics() {
        forkJoin([
            this.statisticsService.getGroupNumber(),
            this.statisticsService.getProjectNumber(),
            this.statisticsService.getCreatedTaskNumber(),
            this.statisticsService.getCompletedTaskNumber(),
            this.statisticsService.getDevelopmentLogSum()
        ]).subscribe(([groupNumber, projectNumber, createdTaskNummber, completedTaskNumber, developmentLogSum]) => {
            this.groupNumber = groupNumber;
            this.projectNumber = projectNumber;
            this.createdTaskNummber = createdTaskNummber;
            this.completedTaskNumber = completedTaskNumber;
            this.developmentLogSum = developmentLogSum;
        });
    }

    private initDevelopmentLogByGroup() {
        this.statisticsService.getDevelopmentLogByGroup().subscribe(map => {
            const keys = [] as string[];
            const values = [] as number[];
            let diagramMax = 0;
            for (const [key, value] of Object.entries(map)) {
                if (diagramMax < value) {
                    diagramMax = value;
                }
                keys.push(key);
                values.push(value);
            }
            this.initDevelopmentLogByProject(keys, values, diagramMax);
        });
    }

    private initDevelopmentLog() {
        this.statisticsService.getDevelopmentLog().subscribe(map => {
            const keys = [] as string[];
            const values = [] as number[];
            for (const [key, value] of Object.entries(map)) {
                keys.push(key.substr(0, 10));
                values.push(value);
            }
            this.initLastSevenDayDevelop(keys, values);
        });
    }

    private initLastSevenDayDevelop(labels: string[], series: number[]): void {
        const dataCompletedTasksChart: any = {
            labels: labels,
            series: [series]
        };

        const optionsCompletedTasksChart: any = {
            lineSmooth: Chartist.Interpolation.cardinal({
                tension: 0
            }),
            low: 0,
            high: 24, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: { top: 0, right: 0, bottom: 0, left: 0 }
        };

        const completedTasksChart = new Chartist.Line('#completedTasksChart', dataCompletedTasksChart, optionsCompletedTasksChart);

        // start animation for the Completed Tasks Chart - Line Chart
        this.startAnimationForLineChart(completedTasksChart);
    }

    private initDevelopmentLogByProject(labels: string[], series: number[], diagramMax: number): void {
        const datawebsiteViewsChart = {
            labels: labels,
            series: [series]
        };
        const optionswebsiteViewsChart = {
            axisX: {
                showGrid: false
            },
            low: 0,
            high: diagramMax,
            chartPadding: { top: 0, right: 0, bottom: 0, left: 0 }
        };

        const responsiveOptions: any[] = [
            ['screen and (max-width: 640px)', {
                seriesBarDistance: 5,
                axisX: {
                    labelInterpolationFnc: function (value) {
                        return value[0];
                    }
                }
            }]
        ];

        const websiteViewsChart =
            new Chartist.Bar('#websiteViewsChart', datawebsiteViewsChart, optionswebsiteViewsChart, responsiveOptions);

        this.startAnimationForBarChart(websiteViewsChart);
    }

}
