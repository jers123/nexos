import { BaseDTO } from "./reply-message.model";

export class ProductCreateDTO extends BaseDTO {
    name!: string;
    quantity!: number;
    idUserCreate?: number;
    idUserUpdate?: number;
    updateDate?: string;
}

export class ProductUpdateDTO extends ProductCreateDTO {
    idProduct!: number;
}