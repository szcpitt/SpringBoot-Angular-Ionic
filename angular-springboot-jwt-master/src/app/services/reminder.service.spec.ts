import { TestBed, inject } from '@angular/core/testing';

import { ReminderService } from './reminder.service';

describe('AddReminderService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ReminderService]
    });
  });

  it('should ...', inject([ReminderService], (service: ReminderService) => {
    expect(service).toBeTruthy();
  }));
});
