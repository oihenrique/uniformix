import { uniformInterface } from "./uniformInterface";

export interface batchInterface {
    description: string,
    quantity: number,
    supplier: number,
    category: number,
    uniform: Array<uniformInterface>
}