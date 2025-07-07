import { HttpStatusCode } from "@angular/common/http";

export abstract class BaseDTO {
  entryDate?: string;
}

export abstract class ReplyMessage {
  uri!: string;
  httpStatus!: HttpStatusCode;
  error!: boolean;
  message!: string[];
  date!: string;
}

export class ReplyMessageList<BDTO extends BaseDTO> extends ReplyMessage {
  response?: BDTO[];
}

export class ReplyMessageSimple<BDTO extends BaseDTO> extends ReplyMessage {
  response?: BDTO;
}