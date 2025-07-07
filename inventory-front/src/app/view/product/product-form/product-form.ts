import { Component, inject, Inject, OnInit } from '@angular/core';
import { UserUpdateDTO } from '../../../models/user.model';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../../services/product.service';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Notificacion } from '../../notificacion/notificacion';
import { ReplyMessageList, ReplyMessageSimple } from '../../../models/reply-message.model';
import { ProductUpdateDTO } from '../../../models/product.model';
import { environment } from '../../../../environments/environment';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule],
  templateUrl: './product-form.html',
  styleUrl: './product-form.css'
})
export class ProductForm implements OnInit {
  form: FormGroup;
  users: UserUpdateDTO[] = [];
  usersU: UserUpdateDTO[] = [];
  replyMessageSimple!: ReplyMessageSimple<ProductUpdateDTO>;
  replyMessageUser!: ReplyMessageList<UserUpdateDTO>;
  isEdit: boolean;
  private dialog = inject(MatDialog);

  constructor(
    private fb: FormBuilder,
    private service: ProductService,
    private dialogRef: MatDialogRef<ProductForm>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.users = data?.users || [];
    this.usersU = this.users;
    this.isEdit = !!data?.idProduct;
    if (data && data.idProduct) {
      this.form = this.fb.group({
        idProduct: [1, [Validators.required, Validators.min(1)]],
        name: ['', Validators.required],
        quantity: [1, [Validators.required, Validators.min(1)]],
        entryDate: ['', Validators.required],
        idUserUpdate: ['', Validators.required],
      });
      this.form.patchValue({
        idProduct: data.idProduct,
        name: data.name,
        quantity: data.quantity,
        entryDate: data.entryDate,
        idUserUpdate: data.idUserUpdate
      });
    } else {
      this.form = this.fb.group({
        name: ['', Validators.required],
        quantity: [1, [Validators.required, Validators.min(1)]],
        idUserCreate: ['', Validators.required],
        entryDate: ['', Validators.required],
      });
    }
  }

  ngOnInit() { }

  save() {
    if (this.form.invalid) return;
    const value = this.form.value;
    if (this.isEdit) {
      this.service.getUpdate(value).subscribe({
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
      this.service.getCreate(value).subscribe({
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
}
