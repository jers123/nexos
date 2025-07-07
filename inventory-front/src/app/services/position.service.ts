import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IBaseService } from './ibase.service';
import { PositionCreateDTO, PositionUpdateDTO } from '../models/position.model';
import { Observable } from 'rxjs';
import { ReplyMessageSimple, ReplyMessageList } from '../models/reply-message.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PositionService implements IBaseService<PositionCreateDTO, PositionUpdateDTO> {

  private urlApi: String = environment.paths.globalPath + environment.paths.subPath.position;

  constructor(private http: HttpClient) {}

  getCreate(entity: PositionCreateDTO): Observable<ReplyMessageSimple<PositionUpdateDTO>> {
    return this.http.post<ReplyMessageSimple<PositionUpdateDTO>>(this.urlApi + environment.paths.functionPath.createPath, entity, environment.httpOptions);
  }

  getFindAll(): Observable<ReplyMessageList<PositionUpdateDTO>> {
    return this.http.get<ReplyMessageList<PositionUpdateDTO>>(this.urlApi + environment.paths.functionPath.getAllPath);
  }

  getFindById(id: number): Observable<ReplyMessageSimple<PositionUpdateDTO>> {
    return this.http.get<ReplyMessageSimple<PositionUpdateDTO>>(this.urlApi + environment.paths.functionPath.getIdPath + id);
  }

  getUpdate(entity: PositionUpdateDTO): Observable<ReplyMessageSimple<PositionUpdateDTO>> {
    return this.http.put<ReplyMessageSimple<PositionUpdateDTO>>(this.urlApi + environment.paths.functionPath.updatePath, entity, environment.httpOptions);
  }
  
  getDelete(id: number): Observable<ReplyMessageSimple<PositionUpdateDTO>> {
    return this.http.delete<ReplyMessageSimple<PositionUpdateDTO>>(this.urlApi + environment.paths.functionPath.deletePath + id, environment.httpOptions);
  }
}
