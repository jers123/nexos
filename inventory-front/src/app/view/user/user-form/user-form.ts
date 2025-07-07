import { Component, inject, Inject, OnInit } from '@angular/core';
import { PositionUpdateDTO } from '../../../models/position.model';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { PositionService } from '../../../services/position.service';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ReplyMessageList, ReplyMessageSimple } from '../../../models/reply-message.model';
import { UserUpdateDTO } from '../../../models/user.model';
import { Notificacion } from '../../notificacion/notificacion';
import { environment } from '../../../../environments/environment';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { CommonModule } from '@angular/common';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule],
  templateUrl: './user-form.html',
  styleUrl: './user-form.css'
})
export class UserForm implements OnInit {
  form: FormGroup;
  positions: PositionUpdateDTO[] = [];
  replyMessageSimple!: ReplyMessageSimple<UserUpdateDTO>;
  replyMessagePosition!: ReplyMessageList<PositionUpdateDTO>;
  isEdit: boolean;
  private dialog = inject(MatDialog);

  constructor(
    private fb: FormBuilder,
    private service: UserService,
    private positionService: PositionService,
    private dialogRef: MatDialogRef<UserForm>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.isEdit = !!data;
    if (data) {
      this.form = this.fb.group({
        idUser: [1, [Validators.required, Validators.min(1)]],
        name: ['', Validators.required],
        age: [18, [Validators.required, Validators.min(18)]],
        idPosition: ['', Validators.required],
        entryDate: ['', Validators.required]
      });
      this.form.patchValue({
        ...data,
        idPosition: data.idPosition
      });
    } else {
      this.form = this.fb.group({
      name: ['', Validators.required],
      age: [18, [Validators.required, Validators.min(18)]],
      idPosition: ['', Validators.required],
      entryDate: ['', Validators.required]
    });
    }
  }

  ngOnInit() {
    this.getPositions();
  }

  save() {
    if (this.form.invalid) return;
    const value = this.form.value;
    const user = {
      ...this.data,
      ...value
    };
    if (this.isEdit) {
      this.service.getUpdate(user).subscribe({
        next: (value) => {
          this.replyMessageSimple = value;
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
      this.service.getCreate(user).subscribe({
        next: (value) => {
          this.replyMessageSimple = value;
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

  getPositions() {
      this.positionService.getFindAll().subscribe({
        next: (value) => {
          this.replyMessagePosition = value;
          this.positions = this.replyMessagePosition.response || [];
        },
        error: (err) => {
          console.log(err);
          if (err.status == 0) {
            this.dialog.open(Notificacion, { data: environment.msg.connectionAPI });
          } else {
            this.replyMessagePosition = err.error;
            this.dialog.open(Notificacion, { data: this.replyMessagePosition.message });
          }
        },
      });
    }
}