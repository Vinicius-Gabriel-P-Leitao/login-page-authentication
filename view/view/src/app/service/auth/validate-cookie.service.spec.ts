import { TestBed } from '@angular/core/testing';

import { ValidateCookieService } from './validate-cookie.service';

describe('ValidateCookieService', () => {
  let service: ValidateCookieService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ValidateCookieService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
