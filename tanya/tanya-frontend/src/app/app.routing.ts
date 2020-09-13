import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PublicHomeComponent } from './components/public-home.component';
import { RegistrationComponent } from './components/guest/registration/registration.component';
import { StartPageComponent } from './components/guest/startpage/start-page.component';
import { LoginComponent } from './components/guest/login/login.component';
import { AuthGuard } from './authguard/auth.guard';
import { PersonDetailsComponent } from './components/user/person-details/person-details.component';
import { ProjectListComponent } from './components/user/project/project-list.component';
import { ListGroupComponent } from './components/user/group/list-group.component';
import { SprintTaskComponent } from './components/user/task-sprint/sprint-task.component';
import { TaskViewComponent } from './components/user/task/task-view.component';
import { CalendarComponent } from './components/user/calendar/calendar.component';
import { TaskCreateComponent } from './components/user/task/task-create/task-create.component';
import { CurrentUserTaskComponent } from './components/user/current-user-task/current-user-task.component';
import { AuthHelperService } from './auth-helper.service';

const appRoutes: Routes = [
    {
        path: '',
        component: PublicHomeComponent,
        children: [
            {
                path: 'home',
                component: StartPageComponent,
                canActivate: [AuthGuard]
            },
            {

                path: 'registration',
                component: RegistrationComponent,
            },
            {
                path: 'login',
                component: LoginComponent,
            },
            { path: 'person-details', component: PersonDetailsComponent, canActivate: [AuthGuard] },
            { path: 'project-list/:id', component: ProjectListComponent, canActivate: [AuthGuard] },
            { path: 'group-list', component: ListGroupComponent, canActivate: [AuthGuard] },
            { path: 'sprint-task-list/:id', component: SprintTaskComponent, canActivate: [AuthGuard] },
            { path: 'task-view', component: TaskViewComponent, canActivate: [AuthGuard] },
            { path: 'task-create/:id', component: TaskCreateComponent, canActivate: [AuthGuard] },
            { path: 'calendar', component: CalendarComponent, canActivate: [AuthGuard] },
            { path: 'current-user-task', component: CurrentUserTaskComponent, canActivate: [AuthGuard] }
        ]
    },
];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule],
    providers: [AuthGuard, AuthHelperService]
})
export class AppRouting {
}
