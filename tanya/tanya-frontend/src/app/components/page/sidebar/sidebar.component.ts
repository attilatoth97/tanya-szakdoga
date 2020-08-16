import { Component, OnInit } from '@angular/core';

declare const $: any;
declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  { path: '/home', title: 'Főoldal', icon: 'dashboard', class: '' },
  { path: '/person-details', title: 'Profil adatok', icon: 'person', class: '' },
  { path: '/group-list', title: 'Csoportok', icon: 'groups', class: '' },
  { path: '/calendar', title: 'Naptár', icon: 'calendar_today', class: '' },
  { path: '/current-user-task', title: 'Feladatok', icon: 'content_paste', class: '' }
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
