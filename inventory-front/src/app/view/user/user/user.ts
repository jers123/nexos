import { Component, inject, OnInit } from '@angular/core';
import { UserUpdateDTO } from '../../../models/user.model';
import { ReplyMessageList, ReplyMessageSimple } from '../../../models/reply-message.model';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { UserService } from '../../../services/user.service';
import { Notificacion } from '../../notificacion/notificacion';
import { environment } from '../../../../environments/environment';
import { UserForm } from '../user-form/user-form';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { PositionService } from '../../../services/position.service';
import { PositionUpdateDTO } from '../../../models/position.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule, MatDialogModule],
  templateUrl: './user.html',
  styleUrl: './user.css'
})
export class User implements OnInit {
  users: UserUpdateDTO[] = [];
  positions: PositionUpdateDTO[] = [];
  replyMessageList!: ReplyMessageList<UserUpdateDTO>;
  replyMessageSimple!: ReplyMessageSimple<UserUpdateDTO>;
  replyMessagePosition!: ReplyMessageList<PositionUpdateDTO>;
  displayedColumns: string[] = ['id', 'nombre', 'edad', 'cargo', 'fechaIngreso', 'acciones'];
  private service = inject(UserService);
  private positionService = inject(PositionService);
  private dialog = inject(MatDialog);
  private router = inject(Router);

  ngOnInit() {
    this.getPositions();
    this.load();
  }

  redirect(url: string): void {
    this.router.navigate([url]);
  }

  load() {
    this.service.getFindAll().subscribe({
      next: (value) => {
        this.replyMessageList = value;
        this.users = this.replyMessageList.response || [];
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

  add() {
    const dialogRef = this.dialog.open(UserForm, {});
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  edit(user: UserUpdateDTO) {
    const dialogRef = this.dialog.open(UserForm, { data: user });
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  delete(id: number) {
    if (confirm('¿Seguro de eliminar este usuario?')) {
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

  getPositionName(id: number): string {
    const position = this.positions.find(p => p.idPosition === id);
    return position ? position.name : 'N/A';
  }
}