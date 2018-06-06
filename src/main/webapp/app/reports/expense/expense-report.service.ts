import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { createRequestOption } from '../../shared';
import {ExpenseReport} from './expense-report.model';

export type EntityResponseType = HttpResponse<ExpenseReport>;

@Injectable()
export class ExpenseReportService {

    private resourceUrl =  SERVER_API_URL + 'api/expense-report';

    constructor(private http: HttpClient) { }

    query(req?: any): Observable<HttpResponse<ExpenseReport[]>> {
        const options = createRequestOption(req);
        return this.http.get<ExpenseReport[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ExpenseReport[]>) => this.convertArrayResponse(res));
    }

    find(year: number, month: number): Observable<HttpResponse<ExpenseReport[]>> {
        // const options = createRequestOption(req);
        return this.http.get<ExpenseReport[]>(`${this.resourceUrl}/${year}/${month}`, {observe: 'response'})
            .map((res: HttpResponse<ExpenseReport[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ExpenseReport = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ExpenseReport[]>): HttpResponse<ExpenseReport[]> {
        const jsonResponse: ExpenseReport[] = res.body;
        const body: ExpenseReport[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Book.
     */
    private convertItemFromServer(expenseReport: ExpenseReport): ExpenseReport {
        const copy: ExpenseReport = Object.assign({}, expenseReport);
        return copy;
    }
}
