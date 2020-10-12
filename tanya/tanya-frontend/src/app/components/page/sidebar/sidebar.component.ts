import { Component, OnInit } from '@angular/core';

declare const $: any;
declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
  sidebar: boolean;
}
export const ROUTES: RouteInfo[] = [
  { path: '/home', title: 'Főoldal', icon: 'dashboard', class: '', sidebar: true },
  { path: '/person-details', title: 'Profil adatok', icon: 'person', class: '', sidebar: true },
  { path: '/group-list', title: 'Csoportok', icon: 'groups', class: '', sidebar: true },
  { path: '/calendar', title: 'Naptár', icon: 'calendar_today', class: '', sidebar: true },
  { path: '/current-user-task', title: 'Feladatok', icon: 'content_paste', class: '', sidebar: true },
  { path: '/current-user-development-log', title: 'Fejlesztési idő', icon: 'access_time', class: '', sidebar: true },
  { path: '/sprint-task-list', title: 'Sprintek', icon: null, class: '', sidebar: false },
  { path: '/project-list', title: 'Projektek', icon: null, class: '', sidebar: false },
  { path: '/task-view', title: 'Feladat megtekintő', icon: null, class: '', sidebar: false },
  { path: '/task-create', title: 'Feladat létrehozó', icon: null, class: '', sidebar: false },
  { path: '/task-view', title: 'Feladat szerkesztő', icon: null, class: '', sidebar: false }

];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
}
