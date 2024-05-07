//import { StockReceiptsDetail } from 'src/app/layout/domain';
import { StockReceiptsDetail } from "./stockreceiptsdetail.model";
export class StockReceipts {
    id?: number;
    docDt?: Date;
    docNo?: string;
    locationId?: any;
    tolocation?: any;
    truckNo?: string;
    driverName?: string;
    comment?: string;
    employee?: any;
    grossAmt?: number;
    discAmt?: number;
    netAmt?: number;
    fldDocno?: string;
    fromLocation?: any;
    verifiedBy?: string;
    userId?:string;
    
    stockReceiptDetailList?: Array<StockReceiptsDetail>
    stocktransfersDetailList?: Array<StockReceiptsDetail>
    prefix?: string;
    fromCNo: number;
    toCNo: number;
    qty: number = 1;
}