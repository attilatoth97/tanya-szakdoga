import { BrowserModule } from '@angular/platform-browser';
import { FullCalendarModule } from '@fullcalendar/angular/';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRouting } from './app.routing';
import { SharedModule } from './components/shared.module';
import { PublicHomeComponent } from './components/public-home.component';
import { LoginComponent } from './components/guest/login/login.component';
import { RegistrationComponent } from './components/guest/registration/registration.component';
import { ProjectListComponent, ProjectDialog, UserAddDialog } from './components/user/project/project-list.component';
import { StartPageComponent } from './components/guest/startpage/start-page.component';
import { PersonDetailsComponent } from './components/user/person-details/person-details.component';
import { ListGroupComponent, CreateOrUpdateGroupDialog } from './components/user/group/list-group.component';
import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material';
import { SprintTaskComponent, SprintDialog } from './components/user/task-sprint/sprint-task.component';
import { TaskViewComponent } from './components/user/task/task-view.component';
import { ToastrModule } from 'ngx-toastr';
import { AuthInterceptor } from './authguard/auth.interceptor';
import { CalendarComponent, CalendarDialog } from './components/user/calendar/calendar.component';
import { CommentViewComponent } from './components/user/comment/comment-view.component';
import { TaskCreateComponent } from './components/user/task/task-create/task-create.component';
import { ResponseInterceptor } from './authguard/responseInterceptor';
import { TaskUpdateComponent } from './components/user/task/task-update/task-update.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CurrentUserTaskComponent } from './components/user/current-user-task/current-user-task.component';
import { SidebarComponent } from './components/page/sidebar/sidebar.component';
import { NavbarComponent } from './components/page/navbar/navbar.component';
import { DevelopmentLogTableComponent } from './components/user/development-log/development-log.table.component';
import { DevelopmentLogDialog, DevelopmentLogPageComponent } from './components/user/development-log/current-user-development-log/development-log-page.component';

@NgModule({
  declarations: [
    AppComponent,
    PublicHomeComponent,
    LoginComponent,
    RegistrationComponent,
    ProjectListComponent,
    StartPageComponent,
    PersonDetailsComponent,
    ListGroupComponent,
    CreateOrUpdateGroupDialog,
    SprintTaskComponent,
    TaskViewComponent,
    ProjectDialog,
    UserAddDialog,
    CalendarComponent,
    SprintDialog,
    CommentViewComponent,
    TaskCreateComponent,
    TaskUpdateComponent,
    CalendarDialog,
    CurrentUserTaskComponent,
    SidebarComponent,
    NavbarComponent,
    DevelopmentLogPageComponent,
    DevelopmentLogTableComponent,
    DevelopmentLogDialog
  ],
  entryComponents: [
    CreateOrUpdateGroupDialog,
    ProjectDialog,
    UserAddDialog,
    SprintDialog,
    CalendarDialog,
    DevelopmentLogDialog
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRouting,
    SharedModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    FullCalendarModule
  ],
  providers: [{ provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: { hasBackdrop: true } }, {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }, {
    provide: HTTP_INTERCEPTORS,
    useClass: ResponseInterceptor,
    multi: true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
