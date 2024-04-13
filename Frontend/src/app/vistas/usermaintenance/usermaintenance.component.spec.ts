import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserMaintenanceComponent } from './usermaintenance.component';

describe('UsermaintenanceComponent', () => {
  let component: UserMaintenanceComponent;
  let fixture: ComponentFixture<UserMaintenanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserMaintenanceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserMaintenanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
