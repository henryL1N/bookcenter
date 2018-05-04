/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { BookCenterTestModule } from '../../../test.module';
import { BookCenterDetailComponent } from '../../../../../../main/webapp/app/entities/book-center/book-center-detail.component';
import { BookCenterService } from '../../../../../../main/webapp/app/entities/book-center/book-center.service';
import { BookCenter } from '../../../../../../main/webapp/app/entities/book-center/book-center.model';

describe('Component Tests', () => {

    describe('BookCenter Management Detail Component', () => {
        let comp: BookCenterDetailComponent;
        let fixture: ComponentFixture<BookCenterDetailComponent>;
        let service: BookCenterService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BookCenterTestModule],
                declarations: [BookCenterDetailComponent],
                providers: [
                    BookCenterService
                ]
            })
            .overrideTemplate(BookCenterDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BookCenterDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BookCenterService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BookCenter(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.bookCenter).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
