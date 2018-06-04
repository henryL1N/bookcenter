import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { createRequestOption } from '../../shared';
import {SalaryReport} from './salary-report.model';

export type EntityResponseType = HttpResponse<SalaryReport>;

@Injectable()
export class SalaryReportService {

    private resourceUrl =  SERVER_API_URL + 'api/salary-report';

    constructor(private http: HttpClient) { }

    query(req?: any): Observable<HttpResponse<SalaryReport[]>> {
        const options = createRequestOption(req);
        return this.http.get<SalaryReport[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SalaryReport[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SalaryReport = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SalaryReport[]>): HttpResponse<SalaryReport[]> {
        const jsonResponse: SalaryReport[] = res.body;
        const body: SalaryReport[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Book.
     */
    private convertItemFromServer(salaryReport: SalaryReport): SalaryReport {
        const copy: SalaryReport = Object.assign({}, salaryReport);
        return copy;
    }
}
