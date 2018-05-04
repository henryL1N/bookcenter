/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BookCenterTestModule } from '../../../test.module';
import { BookCenterComponent } from '../../../../../../main/webapp/app/entities/book-center/book-center.component';
import { BookCenterService } from '../../../../../../main/webapp/app/entities/book-center/book-center.service';
import { BookCenter } from '../../../../../../main/webapp/app/entities/book-center/book-center.model';

describe('Component Tests', () => {

    describe('BookCenter Management Component', () => {
        let comp: BookCenterComponent;
        let fixture: ComponentFixture<BookCenterComponent>;
        let service: BookCenterService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BookCenterTestModule],
                declarations: [BookCenterComponent],
                providers: [
                    BookCenterService
                ]
            })
            .overrideTemplate(BookCenterComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BookCenterComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BookCenterService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BookCenter(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.bookCenters[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
