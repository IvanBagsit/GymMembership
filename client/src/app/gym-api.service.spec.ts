import { TestBed } from '@angular/core/testing';

import { GymApiService } from './gym-api.service';

describe('GymApiService', () => {
  let service: GymApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GymApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
