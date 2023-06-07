import { Component } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl, UntypedFormBuilder, Validators, AbstractControl } from '@angular/forms';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { User } from 'src/app/user';
import { FileHandle } from './helpers/file-handle.model';
import {FormGroup,FormControl} from '@angular/forms';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import Validation from './helpers/Validation';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {


  user: User = {
    userImages: "",
    email: "",
    password: ""
  }
  register: UntypedFormGroup = new UntypedFormGroup({

    email: new UntypedFormControl(''),
    password: new UntypedFormControl(''),
    confirmPassword: new UntypedFormControl(''),
    acceptTerms: new UntypedFormControl(false),
  });

  login = new FormGroup({
    email:new FormControl(''),
    password:new FormControl('')
  })
  submitted = false;
  private file!: File;
  fileToUpload!: File;

  url: string = "assets/profile-image-circle.png";

  constructor(private formBuilder: UntypedFormBuilder, private authService: AuthServiceService,
    private router: Router, private sanitizer: DomSanitizer) {

  }
  ngOnInit(): void {
    this.register = this.formBuilder.group(
      {

        email: ['', [Validators.required, Validators.email]],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        confirmPassword: ['', Validators.required],
        acceptTerms: [false, Validators.requiredTrue]
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]

      }
    );
  }



  get registerFormControl(){
    return this.register.controls;
  }

  get userImages() {
    return this.register.get('userImages');
  }

  get email() {
    return this.register.get(['email']);
  }

  get Password() {
    return this.register.get('password');
  }

  get ConfirmPassword() {
    return this.register.get('confirmPassword');
  }

  signup() {
    this.submitted = true;
    let pass = this.Password?.value;

    let confirmPass = this.ConfirmPassword?.value;
console.log(this.file);
this.router.navigate(['/login'])

    if (pass == confirmPass) {
      this.user.email = this.email?.value;
      this.user.password = this.Password?.value;

      const userFormData = this.prepareFormData(this.user);

      this.authService.registerUser(userFormData).subscribe(
        (data: any) => {
          console.warn(data);
          console.log("registered succesfully");
          this.router.navigate(['/login'])
        },
        error => {
          console.log("error occur while registring User. please try after sometime.");
        }
      )

    }
  }

  prepareFormData(user: User): FormData {
    const formData = new FormData();
    formData.append(
      'file', this.file)
    formData.append(
      'email', user.email
    )
    formData.append('password', user.password)
    return formData;
  }

  onFileSelected(event: any) {
    if (event.target.files) {
      this.file = event.target.files[0];
      const fileHandle: FileHandle = {
        file: this.file,
        url: this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(this.file))

      }
    }
    var reader = new FileReader();
    reader.onload = (event: any) => {
      this.url = event.target.result;
    }
    reader.readAsDataURL(this.file);
  }

 
  signin(){
    console.warn(this.login.value);
    this.authService.loginUser(this.login.value).subscribe((result=>{
      sessionStorage.setItem('token', result.message.substring(6));
      console.log("login succesfully");
      this.router.navigate(['/movie']);

    }))
  }  
  }

