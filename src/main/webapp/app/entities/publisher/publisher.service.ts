import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Publisher } from './publisher.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Publisher>;

@Injectable()
export class PublisherService {

    private resourceUrl =  SERVER_API_URL + 'api/publishers';

    constructor(private http: HttpClient) { }

    create(publisher: Publisher): Observable<EntityResponseType> {
        const copy = this.convert(publisher);
        return this.http.post<Publisher>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(publisher: Publisher): Observable<EntityResponseType> {
        const copy = this.convert(publisher);
        return this.http.put<Publisher>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Publisher>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Publisher[]>> {
        const options = createRequestOption(req);
        return this.http.get<Publisher[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Publisher[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Publisher = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Publisher[]>): HttpResponse<Publisher[]> {
        const jsonResponse: Publisher[] = res.body;
        const body: Publisher[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Publisher.
     */
    private convertItemFromServer(publisher: Publisher): Publisher {
        const copy: Publisher = Object.assign({}, publisher);
        return copy;
    }

    /**
     * Convert a Publisher to a JSON which can be sent to the server.
     */
    private convert(publisher: Publisher): Publisher {
        const copy: Publisher = Object.assign({}, publisher);
        return copy;
    }
}
