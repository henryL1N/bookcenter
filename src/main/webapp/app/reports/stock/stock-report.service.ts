import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { createRequestOption } from '../../shared';
import {StockReport} from './stock-report.model';

export type EntityResponseType = HttpResponse<StockReport>;

@Injectable()
export class StockReportService {

    private resourceUrl =  SERVER_API_URL + 'api/stock-report';

    constructor(private http: HttpClient) { }

    query(req?: any): Observable<HttpResponse<StockReport[]>> {
        const options = createRequestOption(req);
        return this.http.get<StockReport[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<StockReport[]>) => this.convertArrayResponse(res));
    }

    find(warehouseId: number, year: number, month: number): Observable<HttpResponse<StockReport[]>> {
        // const options = createRequestOption(req);
        return this.http.get<StockReport[]>(`${this.resourceUrl}/${warehouseId}/${year}/${month}`, {observe: 'response'})
            .map((res: HttpResponse<StockReport[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: StockReport = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<StockReport[]>): HttpResponse<StockReport[]> {
        const jsonResponse: StockReport[] = res.body;
        const body: StockReport[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Book.
     */
    private convertItemFromServer(stockReport: StockReport): StockReport {
        const copy: StockReport = Object.assign({}, stockReport);
        return copy;
    }
}
