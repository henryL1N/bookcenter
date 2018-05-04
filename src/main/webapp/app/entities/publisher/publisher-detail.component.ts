import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Publisher } from './publisher.model';
import { PublisherService } from './publisher.service';

@Component({
    selector: 'jhi-publisher-detail',
    templateUrl: './publisher-detail.component.html'
})
export class PublisherDetailComponent implements OnInit, OnDestroy {

    publisher: Publisher;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private publisherService: PublisherService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPublishers();
    }

    load(id) {
        this.publisherService.find(id)
            .subscribe((publisherResponse: HttpResponse<Publisher>) => {
                this.publisher = publisherResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPublishers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'publisherListModification',
            (response) => this.load(this.publisher.id)
        );
    }
}
