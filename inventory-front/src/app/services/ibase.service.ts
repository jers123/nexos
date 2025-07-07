import { Observable } from "rxjs";
import { BaseDTO, ReplyMessageList, ReplyMessageSimple } from "../models/reply-message.model";

export interface IBaseService<BC extends BaseDTO, BU extends BaseDTO> {
    getCreate(entity: BC): Observable<ReplyMessageSimple<BU>>;
    getFindAll(): Observable<ReplyMessageList<BU>>;
    getFindById(id: number): Observable<ReplyMessageSimple<BU>>;
    getUpdate(entity: BU): Observable<ReplyMessageSimple<BU>>;
    getDelete(id: number): Observable<ReplyMessageSimple<BU>>;
}