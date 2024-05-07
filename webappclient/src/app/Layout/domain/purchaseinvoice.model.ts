import { PurchaseInvoiceDetail } from "./purchaseinvoicedetail.model";

export class PurchaseInvoice {
    
id?: number;
grossAmt?: number;
docDt?: Date;
docNo?: string;
partyId?: any;
exciseduty?: string;
address?: string;
city?: string;
pin?: string;
state?: string;
payterms?: string;
transporter?: string;
salestax?: number;
cst?: string;
vatamt?: number;
cstamt?: number;
exciseamt?: number;
potype?: string;
discount1?: number;
locationId?: any;
otherCharges?: number;
locationCode?: string;
statusCode?: string;
approvalComments?: string;
address2?: string;
phoneNo?: string;
faxNo?: string;
refNo?: string;
pOearlierno?: string;
refdt?: string;
pOearlierdt?: string;
freight?: number;
freightType?: string;
modeDespatch?: string;
destination?: string;
authorisedTransp?: string;
pkgFwdAmt?: number;
tOTax?: string;
priceBasis?: string;
enqNo?: string;
enq?: string;
discountPc?: number;
discountAmt?: number;
discPcAmt?: number;
discountProdWise?: string;
eDTypePO?: string;
taxtypePO?: string;
email?: string;
tINNo?: string;
payterms2?: string;
payterms3?: string;
payterms4?: string;
remarks?: string;
tOTpc?: string;
department?: string;
ourRefNo?: string;
code?: string;
pkgFwdPc?: string;
pkgFwdType?: string;
address3?: string;
taxTypeNote?: string;
versionNo?: number;
fldDept?: string;
baseDocNo?: string;
baseDocDt?: Date;
vchId?: number;
amt?: number;
location?: string;
userId: string;
purchaseInvoiveDetList?: Array<PurchaseInvoiceDetail>
 
  
}