import { NgModule } from '@angular/core';
import {
  MatToolbarModule, MatButtonModule, MatInputModule,
  MatDialogModule, MatNativeDateModule, MatCardModule, MatIconModule, MatSelectModule, MatExpansionModule
} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule } from '@angular/material/tabs';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatTableModule } from '@angular/material/table';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatDialogModule,
    MatTabsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatIconModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatExpansionModule


  ],
  exports: [
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatDialogModule,
    MatTabsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatExpansionModule
  ]
})
export class SharedModule {

}
