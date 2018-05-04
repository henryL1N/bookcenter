import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { BookCenter } from './book-center.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BookCenter>;

@Injectable()
export class BookCenterService {

    private resourceUrl =  SERVER_API_URL + 'api/book-centers';

    constructor(private http: HttpClient) { }

    create(bookCenter: BookCenter): Observable<EntityResponseType> {
        const copy = this.convert(bookCenter);
        return this.http.post<BookCenter>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(bookCenter: BookCenter): Observable<EntityResponseType> {
        const copy = this.convert(bookCenter);
        return this.http.put<BookCenter>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BookCenter>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BookCenter[]>> {
        const options = createRequestOption(req);
        return this.http.get<BookCenter[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BookCenter[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BookCenter = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BookCenter[]>): HttpResponse<BookCenter[]> {
        const jsonResponse: BookCenter[] = res.body;
        const body: BookCenter[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BookCenter.
     */
    private convertItemFromServer(bookCenter: BookCenter): BookCenter {
        const copy: BookCenter = Object.assign({}, bookCenter);
        return copy;
    }

    /**
     * Convert a BookCenter to a JSON which can be sent to the server.
     */
    private convert(bookCenter: BookCenter): BookCenter {
        const copy: BookCenter = Object.assign({}, bookCenter);
        return copy;
    }
}
