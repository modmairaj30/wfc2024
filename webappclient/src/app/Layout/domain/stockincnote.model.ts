import { StockInCNoteDetail } from ".";


export class StockInCNote {
    
    id?: number;
    docDt?: Date;
    docNo?: string;
    locationId?: any;
    userId?: string;
    customer?: string;
    uid?: string;
    cPrefix?: string;
    count?: number;
    fromNo?: number;
    toNo?: number;
    stockincnoteDetailList?: Array<StockInCNoteDetail>
        
    
}