import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PublicHomeComponent } from './components/public-home.component';
import { RegistrationComponent } from './components/guest/registration/registration.component';
import { StartPageComponent } from './components/guest/startpage/start-page.component';
import { LoginComponent } from './components/guest/login/login.component';
import { AuthguardGuard } from './authguard/authguard.guard';
import { PersonDetailsComponent } from './components/user/person-details/person-details.component';
import { ProjectListComponent } from './components/user/project/project-list.component';
import { ListGroupComponent } from './components/user/group/list-group.component';
import { SprintTaskComponent } from './components/user/task-sprint/sprint-task.component';
import { TaskViewComponent } from './components/user/task/task-view.component';
import { CalendarComponent } from './components/user/calendar/calendar.component';
import { TaskCreateComponent } from './components/user/task/task-create/task-create.component';
import { CurrentUserTaskComponent } from './components/user/current-user-task/current-user-task.component';

const appRoutes: Routes = [
    {
        path: '',
        component: PublicHomeComponent,
        children: [
            {
                path: 'home',
                component: StartPageComponent,
                canActivate: [AuthguardGuard]
            }
            , {

                path: 'registration',
                component: RegistrationComponent,
            }
            , {
                path: 'login',
                component: LoginComponent,
            },
            { path: 'person-details', component: PersonDetailsComponent },
            { path: 'project-list/:id', component: ProjectListComponent },
            { path: 'group-list', component: ListGroupComponent },
            { path: 'sprint-task-list/:id', component: SprintTaskComponent },
            { path: 'task-view', component: TaskViewComponent },
            { path: 'task-create/:id', component: TaskCreateComponent },
            { path: 'calendar', component: CalendarComponent },
            { path: 'current-user-task', component: CurrentUserTaskComponent }
        ]
    },
];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule],
    providers: [AuthguardGuard]
})
export class AppRouting {
}
