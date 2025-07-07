import { Component, inject, OnInit } from '@angular/core';
import { PositionUpdateDTO } from '../../../models/position.model';
import { PositionService } from '../../../services/position.service';
import { ReplyMessageList, ReplyMessageSimple } from '../../../models/reply-message.model';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { environment } from '../../../../environments/environment';
import { Notificacion } from '../../notificacion/notificacion';
import { PositionForm } from '../position-form/position-form';
import { Router } from '@angular/router';

@Component({
  selector: 'app-position',
  standalone: true,
  imports: [MatTableModule, MatButtonModule, MatIconModule, MatDialogModule],
  templateUrl: './position.html',
  styleUrl: './position.css'
})
export class Position implements OnInit {
  positions: PositionUpdateDTO[] = [];
  replyMessageList!: ReplyMessageList<PositionUpdateDTO>;
  replyMessageSimple!: ReplyMessageSimple<PositionUpdateDTO>;
  displayedColumns: string[] = ['id', 'nombre', 'acciones'];
  private service = inject(PositionService);
  private dialog = inject(MatDialog);
  private router = inject(Router);

  ngOnInit() {
    this.load();
  }

  redirect(url: string): void {
    this.router.navigate([url]);
  }

  load() {
    this.service.getFindAll().subscribe({
      next: (value) => {
        this.replyMessageList = value;
        this.positions = this.replyMessageList.response || [];
      },
      error: (err) => {
        console.log(err);
        if(err.status == 0) {
          this.dialog.open(Notificacion, { data: environment.msg.connectionAPI });
        } else {
          this.replyMessageList = err.error;
          this.dialog.open(Notificacion, { data: this.replyMessageList.message });
        }
      },
    });
  }

  add() {
    const dialogRef = this.dialog.open(PositionForm, {  });
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  edit(position: PositionUpdateDTO) {
    const dialogRef = this.dialog.open(PositionForm, { data: position });
    dialogRef.afterClosed().subscribe(result => { if (result) this.load(); });
  }

  delete(id: number) {
    if (confirm('¿Seguro de eliminar este cargo?')) {
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
}