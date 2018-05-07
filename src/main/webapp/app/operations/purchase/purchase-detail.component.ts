import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { PurchaseOrder } from '../../entities/purchase-order/purchase-order.model';
import { PurchaseOrderService } from '../../entities/purchase-order/purchase-order.service';

@Component({
    selector: 'jhi-purchase-order-detail',
    templateUrl: './purchase-detail.component.html'
})
export class PurchaseDetailComponent implements OnInit, OnDestroy {

    purchaseOrder: PurchaseOrder;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private purchaseOrderService: PurchaseOrderService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPurchaseOrders();
    }

    load(id) {
        this.purchaseOrderService.find(id)
            .subscribe((purchaseOrderResponse: HttpResponse<PurchaseOrder>) => {
                this.purchaseOrder = purchaseOrderResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPurchaseOrders() {
        this.eventSubscriber = this.eventManager.subscribe(
            'purchaseOrderListModification',
            (response) => this.load(this.purchaseOrder.id)
        );
    }
}
