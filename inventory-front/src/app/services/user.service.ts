import { Injectable } from '@angular/core';
import { UserCreateDTO, UserUpdateDTO } from '../models/user.model';
import { environment } from '../../environments/environment';
import { ReplyMessageList, ReplyMessageSimple } from '../models/reply-message.model';
import { IBaseService } from './ibase.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService implements IBaseService<UserCreateDTO, UserUpdateDTO> {

  private urlApi: String = environment.paths.globalPath + environment.paths.subPath.user;

  constructor(private http: HttpClient) {}

  getCreate(entity: UserCreateDTO): Observable<ReplyMessageSimple<UserUpdateDTO>> {
    return this.http.post<ReplyMessageSimple<UserUpdateDTO>>(this.urlApi + environment.paths.functionPath.createPath, entity, environment.httpOptions);
  }

  getFindAll(): Observable<ReplyMessageList<UserUpdateDTO>> {
    return this.http.get<ReplyMessageList<UserUpdateDTO>>(this.urlApi + environment.paths.functionPath.getAllPath);
  }

  getFindById(id: number): Observable<ReplyMessageSimple<UserUpdateDTO>> {
    return this.http.get<ReplyMessageSimple<UserUpdateDTO>>(this.urlApi + environment.paths.functionPath.getIdPath + id);
  }

  getUpdate(entity: UserUpdateDTO): Observable<ReplyMessageSimple<UserUpdateDTO>> {
    return this.http.put<ReplyMessageSimple<UserUpdateDTO>>(this.urlApi + environment.paths.functionPath.updatePath, entity, environment.httpOptions);
  }
  
  getDelete(id: number): Observable<ReplyMessageSimple<UserUpdateDTO>> {
    return this.http.delete<ReplyMessageSimple<UserUpdateDTO>>(this.urlApi + environment.paths.functionPath.deletePath + id, environment.httpOptions);
  }
}