import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Component, Inject} from '@angular/core';
import {TranslatePipe} from '@ngx-translate/core';

@Component({
  selector: 'app-confirm',
  template: `<h1 matDialogTitle>{{ data.title }}</h1>
  <div mat-dialog-content>{{ data.message }}</div>
  <div mat-dialog-actions>
    <button
      type="button"
      mat-raised-button
      color="primary"
      (click)="dialogRef.close(true)">{{'OK' | translate}}
    </button>
    &nbsp;
    <span fxFlex></span>
    <button
      type="button"
      color="accent"
      mat-raised-button
      (click)="dialogRef.close(false)">{{'CANCEL' | translate}}
    </button>
  </div>`,
})
export class AppComfirmComponent {
  constructor(public dialogRef: MatDialogRef<AppComfirmComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }
}
