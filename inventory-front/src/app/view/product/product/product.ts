import { Component, inject, OnInit } from '@angular/core';
import { UserUpdateDTO } from '../../../models/user.model';
import { ProductUpdateDTO } from '../../../models/product.model';
import { ProductService } from '../../../services/product.service';
import { UserService } from '../../../services/user.service';
import { ReplyMessageList, ReplyMessageSimple } from '../../../models/reply-message.model';
import { ProductForm } from '../product-form/product-form';
import { Notificacion } from '../../notificacion/notificacion';
import { environment } from '../../../../environments/environment';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule, MatDialogModule],
  templateUrl: './product.html',
  styleUrl: './product.css'
})
export class Product implements OnInit {
  products: ProductUpdateDTO[] = [];
  users: UserUpdateDTO[] = [];
  replyMessageList!: ReplyMessageList<ProductUpdateDTO>;
  replyMessageSimple!: ReplyMessageSimple<ProductUpdateDTO>;
  replyMessageUser!: ReplyMessageList<UserUpdateDTO>;
    
  displayedColumns: string[] = ['id', 'nombre', 'cantidad', 'usuarioRegistroId', 'fechaIngreso', 'usuarioActualizoId', 'fechaActualizacion' , 'acciones'];
  private service = inject(ProductService);
  private userService = inject(UserService);
  private dialog = inject(MatDialog);
  private router = inject(Router);

  ngOnInit() {
    this.getUsers();
    this.load();
  }

  redirect(url: string): void {
    this.router.navigate([url]);
  }

  load() {
    this.service.getFindAll().subscribe({
      next: (value) => {
        this.replyMessageList = value;
        this.products = this.replyMessageList.response || [];
      },
      error: (err) => {
        console.log(err);
        if (err.status == 0) {
          this.dialog.open(Notificacion, { data: environment.msg.connectionAPI });
        } else {
          this.replyMessageList = err.error;
          this.dialog.open(Notificacion, { data: this.replyMessageList.message });
        }
      },
    });
  }

  getUserName(id: number) {
    return this.users.find(u => u.idUser === id)?.name || '';
  }

  add() {
    const dialogRef = this.dialog.open(ProductForm, { data: { users: this.users } });
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  edit(product: ProductUpdateDTO) {
    const dialogRef = this.dialog.open(ProductForm, { data: { ...product, usuarios: this.users } });
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  delete(id: number) {
    const usuarioId = prompt('Ingrese su ID de usuario para eliminar:');
    if (usuarioId) {
      this.service.getDelete(id).subscribe({
        next: (value) => {
          this.load();
        },
        error: (err) => {
          console.log(err);
          if(err.status == 0) {
            this.dialog.open(Notificacion, { data: environment.msg.connectionAPI });
          } else {
            this.replyMessageSimple = err.error;
            this.dialog.open(Notificacion, { data: this.replyMessageSimple.message });
          }
        },
      });
    }
  }

  openFilter() {
    //const dialogRef = this.dialog.open(MercanciaFilterComponent, { data: { users: this.users } });
    //dialogRef.afterClosed().subscribe(filtros => { if (filtros) this.load(filtros); });
  }

  getUsers() {
    this.userService.getFindAll().subscribe({
      next: (value) => {
        this.replyMessageUser = value;
        this.users = this.replyMessageUser.response || [];
      },
      error: (err) => {
        console.log(err);
        if (err.status == 0) {
          this.dialog.open(Notificacion, { data: environment.msg.connectionAPI });
        } else {
          this.replyMessageUser = err.error;
          this.dialog.open(Notificacion, { data: this.replyMessageUser.message });
        }
      },
    });
  }
}