import { BaseDTO } from "./reply-message.model";

export class PositionCreateDTO extends BaseDTO {
    name!: string;
}

export class PositionUpdateDTO extends PositionCreateDTO {
    idPosition!: number;
}