import { Component, inject, Inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PositionService } from '../../../services/position.service';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { ReplyMessageSimple } from '../../../models/reply-message.model';
import { PositionUpdateDTO } from '../../../models/position.model';
import { Notificacion } from '../../notificacion/notificacion';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-position-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './position-form.html',
  styleUrl: './position-form.css'
})
export class PositionForm {
  form: FormGroup;
  isEdit: boolean;
  private dialog = inject(MatDialog);

  replyMessageSimple!: ReplyMessageSimple<PositionUpdateDTO>;

  constructor(
    private fb: FormBuilder,
    private service: PositionService,
    private dialogRef: MatDialogRef<PositionForm>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    console.log("data");
    console.log(data);
    if (data) {
      this.form = this.fb.group({
        idPosition: [1, [Validators.required, Validators.min(1)]],
        name: ['', Validators.required]
      });
      this.form.patchValue(data);
    } else {
      this.form = this.fb.group({
        name: ['', Validators.required]
      });
    }

    this.isEdit = !!data;
  }

  save() {
    if (this.form.invalid) return;
    if (this.isEdit) {
      this.service.getUpdate(this.form.value).subscribe({
        next: (value) => {
          this.replyMessageSimple = value;
          console.log(this.replyMessageSimple);
          this.dialogRef.close(true);
        },
        error: (err) => {
          console.log(err);
          if (err.status == 0) {
            this.dialog.open(Notificacion, { data: environment.msg.connectionAPI });
          } else {
            this.replyMessageSimple = err.error;
            this.dialog.open(Notificacion, { data: this.replyMessageSimple.message });
          }
        },
      });
    } else {
      this.service.getCreate(this.form.value).subscribe({
        next: (value) => {
          this.replyMessageSimple = value;
          console.log(this.replyMessageSimple);
          this.dialogRef.close(true);
        },
        error: (err) => {
          console.log(err);
          if (err.status == 0) {
            this.dialog.open(Notificacion, { data: environment.msg.connectionAPI });
          } else {
            this.replyMessageSimple = err.error;
            this.dialog.open(Notificacion, { data: this.replyMessageSimple.message });
          }
        },
      });
    }
  }

  close() {
    this.dialogRef.close();
  }
}