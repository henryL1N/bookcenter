import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { PurchaseOrder } from './purchase-order.model';
import { PurchaseOrderPopupService } from './purchase-order-popup.service';
import { PurchaseOrderService } from './purchase-order.service';
import { Warehouse, WarehouseService } from '../warehouse';
import { Employee, EmployeeService } from '../employee';

@Component({
    selector: 'jhi-purchase-order-dialog',
    templateUrl: './purchase-order-dialog.component.html'
})
export class PurchaseOrderDialogComponent implements OnInit {

    purchaseOrder: PurchaseOrder;
    isSaving: boolean;

    warehouses: Warehouse[];

    employees: Employee[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private purchaseOrderService: PurchaseOrderService,
        private warehouseService: WarehouseService,
        private employeeService: EmployeeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.warehouseService.query()
            .subscribe((res: HttpResponse<Warehouse[]>) => { this.warehouses = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.employeeService.query()
            .subscribe((res: HttpResponse<Employee[]>) => { this.employees = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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

    trackWarehouseById(index: number, item: Warehouse) {
        return item.id;
    }

    trackEmployeeById(index: number, item: Employee) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-purchase-order-popup',
    template: ''
})
export class PurchaseOrderPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private purchaseOrderPopupService: PurchaseOrderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.purchaseOrderPopupService
                    .open(PurchaseOrderDialogComponent as Component, params['id']);
            } else {
                this.purchaseOrderPopupService
                    .open(PurchaseOrderDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
