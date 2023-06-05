import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { AdminService, DatabaseUser, Role } from '../../service/admin.service';
import { MatLegacyPaginator as MatPaginator } from '@angular/material/legacy-paginator';
import { MatLegacyTableDataSource as MatTableDataSource } from '@angular/material/legacy-table';
import { DomSanitizer } from '@angular/platform-browser';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { MatLegacyOption as MatOption } from '@angular/material/legacy-core';
import { MatLegacySelect as MatSelect } from '@angular/material/legacy-select';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class AdminUsersComponent implements OnInit, AfterViewInit {

  user: UntypedFormControl = new UntypedFormControl('');

  allRoles: Role[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  dataSource: MatTableDataSource<DatabaseUser> = new MatTableDataSource(new Array<DatabaseUser>());

  expandedElement!: DatabaseUser;

  changedDatabaseUser: any;

  displayedColumns: string[] = ['id', 'email', 'enabled', 'roles'];

  columnsToDisplayWithExpand: string[] = [...this.displayedColumns, 'expand'];

  constructor(private adminService: AdminService, private http: HttpClient) { }

  ngOnInit(): void {
    // this.adminService.adminLogin().subscribe(res => {
    //   sessionStorage.setItem('token', res.message.substring(6));
      this.adminService.getRoles().subscribe(res => this.allRoles = res);
    // });
    this.changedDatabaseUser = new UntypedFormGroup({
      id: new UntypedFormControl({ value: '', disabled: true, }),
      email: new UntypedFormControl({ value: '', disabled: true }),
      enabled: new UntypedFormControl(false),
      accountExpiryDate: new UntypedFormControl({ value: '', disabled: true }),
      accountNonLocked: new UntypedFormControl({ value: false, disabled: true }),
      credentialsExpiryDate: new UntypedFormControl({ value: '', disabled: true }),
      roles: new UntypedFormControl(new Array<Role>())
    }
    )
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  loadRowDetails(databaseUser: DatabaseUser) {
    if (this.expandedElement) {
      this.changedDatabaseUser?.controls['enabled'].setValue(this.expandedElement.enabled);
      this.changedDatabaseUser?.controls['roles'].setValue(this.expandedElement.roles);
      //      this.changedDatabaseUser?.controls['accountNonLocked'].setValue(this.expandedElement.accountNonLocked);
      if(databaseUser.email === 'test@test.com')
        this.changedDatabaseUser?.controls['roles'].disable();
      this.changedDatabaseUser?.patchValue(this.expandedElement);
    }
    this.changedDatabaseUser?.markAsPristine();
  }

  search() {
    this.adminService.getUsersByEmailContainingString(this.user.value).subscribe(res => {
      this.dataSource.data = res;
    });
  }

  submit() {
    this.adminService.updateUser(this.changedDatabaseUser?.getRawValue()).subscribe(res => this.search());
  }

  compareWith(o1: any, o2: any): boolean {
    return (o1?.name as string) === (o2?.name as string);
  }
}

// id: number;
// email: string;
// enabled: boolean;
// accountExpiryDate: string;
// accountNonLocked: boolean;
// credentialsExpiryDate: string;
// roles: Role[];
