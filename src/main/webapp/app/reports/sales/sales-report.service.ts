import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { createRequestOption } from '../../shared';
import {SalesReport} from './sales-report.model';

export type EntityResponseType = HttpResponse<SalesReport>;

@Injectable()
export class SalesReportService {

    private resourceUrl =  SERVER_API_URL + 'api/sales-report';

    constructor(private http: HttpClient) { }

    query(req?: any): Observable<HttpResponse<SalesReport[]>> {
        const options = createRequestOption(req);
        return this.http.get<SalesReport[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SalesReport[]>) => this.convertArrayResponse(res));
    }

    find(departmentId: number, year: number, month: number): Observable<HttpResponse<SalesReport[]>> {
        // const options = createRequestOption(req);
        return this.http.get<SalesReport[]>(`${this.resourceUrl}/${departmentId}/${year}/${month}`, {observe: 'response'})
            .map((res: HttpResponse<SalesReport[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SalesReport = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SalesReport[]>): HttpResponse<SalesReport[]> {
        const jsonResponse: SalesReport[] = res.body;
        const body: SalesReport[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Book.
     */
    private convertItemFromServer(salesReport: SalesReport): SalesReport {
        const copy: SalesReport = Object.assign({}, salesReport);
        return copy;
    }
}
