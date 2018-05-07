import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { PurchaseOrder } from '../../entities/purchase-order/purchase-order.model';
import { PurchaseOrderPopupService } from '../../entities/purchase-order/purchase-order-popup.service';
import { PurchaseOrderService } from '../../entities/purchase-order/purchase-order.service';
import { Employee, EmployeeService } from '../../entities/employee';

@Component({
    selector: 'jhi-purchase-order-dialog',
    templateUrl: './purchase-dialog.component.html'
})
export class PurchaseDialogComponent implements OnInit {

    purchaseOrder: PurchaseOrder;
    isSaving: boolean;

    buyers: Employee[];
    dateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private purchaseOrderService: PurchaseOrderService,
        private employeeService: EmployeeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.employeeService
            .query({filter: 'purchaseorder-is-null'})
            .subscribe((res: HttpResponse<Employee[]>) => {
                if (!this.purchaseOrder.buyer || !this.purchaseOrder.buyer.id) {
                    this.buyers = res.body;
                } else {
                    this.employeeService
                        .find(this.purchaseOrder.buyer.id)
                        .subscribe((subRes: HttpResponse<Employee>) => {
                            this.buyers = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.purchaseOrder.id !== undefined) {
            this.subscribeToSaveResponse(
                this.purchaseOrderService.update(this.purchaseOrder));
        } else {
            this.subscribeToSaveResponse(
                this.purchaseOrderService.create(this.purchaseOrder));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<PurchaseOrder>>) {
        result.subscribe((res: HttpResponse<PurchaseOrder>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: PurchaseOrder) {
        this.eventManager.broadcast({ name: 'purchaseOrderListModification', content: 'OK'});
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
    selector: 'jhi-purchase-order-popup',
    template: ''
})
export class PurchasePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private purchaseOrderPopupService: PurchaseOrderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.purchaseOrderPopupService
                    .open(PurchaseDialogComponent as Component, params['id']);
            } else {
                this.purchaseOrderPopupService
                    .open(PurchaseDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
