import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BookCenter } from './book-center.model';
import { BookCenterPopupService } from './book-center-popup.service';
import { BookCenterService } from './book-center.service';

@Component({
    selector: 'jhi-book-center-delete-dialog',
    templateUrl: './book-center-delete-dialog.component.html'
})
export class BookCenterDeleteDialogComponent {

    bookCenter: BookCenter;

    constructor(
        private bookCenterService: BookCenterService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bookCenterService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'bookCenterListModification',
                content: 'Deleted an bookCenter'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-book-center-delete-popup',
    template: ''
})
export class BookCenterDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bookCenterPopupService: BookCenterPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.bookCenterPopupService
                .open(BookCenterDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
