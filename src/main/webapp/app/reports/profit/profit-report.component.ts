import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {ProfitReport} from './profit-report.model';
import {ProfitReportService} from './profit-report.service';

@Component({
    selector: 'jhi-profit-report',
    templateUrl: './profit-report.component.html'
})
export class ProfitReportComponent implements OnInit, OnDestroy {

currentAccount: any;
    profitReports: ProfitReport[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    year: number;
    month: number;
    years: number[];
    months: number[];

    constructor(
        private profitReportService: ProfitReportService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.profitReportService.query({}).subscribe(
            (res: HttpResponse<ProfitReport[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
    transition() {
        this.loadAll();
    }

    clear() {
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
    }

    ngOnDestroy() {
    }

    trackId(index: number, item: ProfitReport) {
        return item.id;
    }

    private onSuccess(data, headers) {
        this.profitReports = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
