import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {SalaryReport} from './salary-report.model';
import {SalaryReportService} from './salary-report.service';

@Component({
    selector: 'jhi-salary-report',
    templateUrl: './salary-report.component.html'
})
export class SalaryReportComponent implements OnInit, OnDestroy {

currentAccount: any;
    salaryReports: SalaryReport[];
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
        private salaryReportService: SalaryReportService,
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
        this.salaryReportService.query({}).subscribe(
            (res: HttpResponse<SalaryReport[]>) => this.onSuccess(res.body, res.headers),
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

    trackId(index: number, item: SalaryReport) {
        return item.id;
    }

    private onSuccess(data, headers) {
        this.salaryReports = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
