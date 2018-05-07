import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SalesOrder } from './sales-order.model';
import { SalesOrderPopupService } from './sales-order-popup.service';
import { SalesOrderService } from './sales-order.service';
import { Employee, EmployeeService } from '../employee';

@Component({
    selector: 'jhi-sales-order-dialog',
    templateUrl: './sales-order-dialog.component.html'
})
export class SalesOrderDialogComponent implements OnInit {

    salesOrder: SalesOrder;
    isSaving: boolean;

    sellers: Employee[];
    dateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private salesOrderService: SalesOrderService,
        private employeeService: EmployeeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.employeeService
            .query({filter: 'salesorder-is-null'})
            .subscribe((res: HttpResponse<Employee[]>) => {
                if (!this.salesOrder.seller || !this.salesOrder.seller.id) {
                    this.sellers = res.body;
                } else {
                    this.employeeService
                        .find(this.salesOrder.seller.id)
                        .subscribe((subRes: HttpResponse<Employee>) => {
                            this.sellers = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.salesOrder.id !== undefined) {
            this.subscribeToSaveResponse(
                this.salesOrderService.update(this.salesOrder));
        } else {
            this.subscribeToSaveResponse(
                this.salesOrderService.create(this.salesOrder));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SalesOrder>>) {
        result.subscribe((res: HttpResponse<SalesOrder>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SalesOrder) {
        this.eventManager.broadcast({ name: 'salesOrderListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackEmployeeById(index: number, item: Employee) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-sales-order-popup',
    template: ''
})
export class SalesOrderPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private salesOrderPopupService: SalesOrderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.salesOrderPopupService
                    .open(SalesOrderDialogComponent as Component, params['id']);
            } else {
                this.salesOrderPopupService
                    .open(SalesOrderDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}