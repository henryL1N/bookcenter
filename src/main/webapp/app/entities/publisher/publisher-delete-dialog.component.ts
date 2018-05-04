import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Publisher } from './publisher.model';
import { PublisherPopupService } from './publisher-popup.service';
import { PublisherService } from './publisher.service';

@Component({
    selector: 'jhi-publisher-delete-dialog',
    templateUrl: './publisher-delete-dialog.component.html'
})
export class PublisherDeleteDialogComponent {

    publisher: Publisher;

    constructor(
        private publisherService: PublisherService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.publisherService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'publisherListModification',
                content: 'Deleted an publisher'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-publisher-delete-popup',
    template: ''
})
export class PublisherDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private publisherPopupService: PublisherPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.publisherPopupService
                .open(PublisherDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
