import { BaseDTO } from "./reply-message.model";

export class UserCreateDTO extends BaseDTO {
    name!: string;
    age!: number;
    idPosition!: number;
}

export class UserUpdateDTO extends UserCreateDTO {
    idUser!: number;
}