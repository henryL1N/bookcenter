import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { createRequestOption } from '../../shared';
import {ProfitReport} from './profit-report.model';

export type EntityResponseType = HttpResponse<ProfitReport>;

@Injectable()
export class ProfitReportService {

    private resourceUrl =  SERVER_API_URL + 'api/profit-report';

    constructor(private http: HttpClient) { }

    query(req?: any): Observable<HttpResponse<ProfitReport[]>> {
        const options = createRequestOption(req);
        return this.http.get<ProfitReport[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ProfitReport[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ProfitReport = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ProfitReport[]>): HttpResponse<ProfitReport[]> {
        const jsonResponse: ProfitReport[] = res.body;
        const body: ProfitReport[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Book.
     */
    private convertItemFromServer(profitReport: ProfitReport): ProfitReport {
        const copy: ProfitReport = Object.assign({}, profitReport);
        return copy;
    }
}
