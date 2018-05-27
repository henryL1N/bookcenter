import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Sale } from './sale.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Sale>;

@Injectable()
export class SaleService {

    private resourceUrl =  SERVER_API_URL + 'api/sales';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(sale: Sale): Observable<EntityResponseType> {
        const copy = this.convert(sale);
        return this.http.post<Sale>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(sale: Sale): Observable<EntityResponseType> {
        const copy = this.convert(sale);
        return this.http.put<Sale>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Sale>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Sale[]>> {
        const options = createRequestOption(req);
        return this.http.get<Sale[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Sale[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Sale = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Sale[]>): HttpResponse<Sale[]> {
        const jsonResponse: Sale[] = res.body;
        const body: Sale[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Sale.
     */
    private convertItemFromServer(sale: Sale): Sale {
        const copy: Sale = Object.assign({}, sale);
        copy.date = this.dateUtils
            .convertDateTimeFromServer(sale.date);
        return copy;
    }

    /**
     * Convert a Sale to a JSON which can be sent to the server.
     */
    private convert(sale: Sale): Sale {
        const copy: Sale = Object.assign({}, sale);

        copy.date = this.dateUtils.toDate(sale.date);
        return copy;
    }
}
