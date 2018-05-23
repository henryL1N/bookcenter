import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Purchase } from './purchase.model';
import { PurchaseService } from './purchase.service';
import {OrderItem} from '../../entities/order-item/order-item.model';

@Component({
    selector: 'jhi-purchase-detail',
    templateUrl: './purchase-detail.component.html'
})
export class PurchaseDetailComponent implements OnInit, OnDestroy {

    purchase: Purchase;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private purchaseService: PurchaseService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPurchases();
    }

    load(id) {
        this.purchaseService.find(id)
            .subscribe((purchaseResponse: HttpResponse<Purchase>) => {
                this.purchase = purchaseResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPurchases() {
        this.eventSubscriber = this.eventManager.subscribe(
            'purchaseListModification',
            (response) => this.load(this.purchase.id)
        );
    }

    trackOrderItemId(index: number, item: OrderItem) {
        return item.id;
    }
}
