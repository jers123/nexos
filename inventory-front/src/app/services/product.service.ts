import { Injectable } from '@angular/core';
import { IBaseService } from './ibase.service';
import { ProductCreateDTO, ProductUpdateDTO } from '../models/product.model';
import { environment } from '../../environments/environment';
import { ReplyMessageList, ReplyMessageSimple } from '../models/reply-message.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService implements IBaseService<ProductCreateDTO, ProductUpdateDTO> {

  private urlApi: String = environment.paths.globalPath + environment.paths.subPath.product;

  constructor(private http: HttpClient) {}

  getCreate(entity: ProductCreateDTO): Observable<ReplyMessageSimple<ProductUpdateDTO>> {
    return this.http.post<ReplyMessageSimple<ProductUpdateDTO>>(this.urlApi + environment.paths.functionPath.createPath, entity, environment.httpOptions);
  }

  getFindAll(): Observable<ReplyMessageList<ProductUpdateDTO>> {
    return this.http.get<ReplyMessageList<ProductUpdateDTO>>(this.urlApi + environment.paths.functionPath.getAllPath);
  }

  getFindById(id: number): Observable<ReplyMessageSimple<ProductUpdateDTO>> {
    return this.http.get<ReplyMessageSimple<ProductUpdateDTO>>(this.urlApi + environment.paths.functionPath.getIdPath + id);
  }

  getUpdate(entity: ProductUpdateDTO): Observable<ReplyMessageSimple<ProductUpdateDTO>> {
    return this.http.put<ReplyMessageSimple<ProductUpdateDTO>>(this.urlApi + environment.paths.functionPath.updatePath, entity, environment.httpOptions);
  }
  
  getDelete(id: number): Observable<ReplyMessageSimple<ProductUpdateDTO>> {
    return this.http.delete<ReplyMessageSimple<ProductUpdateDTO>>(this.urlApi + environment.paths.functionPath.deletePath + id, environment.httpOptions);
  }
}