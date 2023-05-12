import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Details } from '../model/model';
import { Router } from '@angular/router';
import { UploadService } from '../upload.service';


@Component({
  selector: 'app-view1form',
  templateUrl: './view1form.component.html',
  styleUrls: ['./view1form.component.css']
})
export class View1formComponent implements OnInit {
  form!: FormGroup;
  userDetails: Details[] = [];

  constructor(private fb: FormBuilder, private router: Router, private uploadService: UploadService) {}

  ngOnInit(): void {
    this.createForm();
  }

  submitForm(): void {
    if (this.form.valid) {
      const details: Details = {
        name: this.form.get('name')?.value,
        title: this.form.get('title')?.value,
        comments: this.form.get('comments')?.value,
        archive: this.form.get('archive')?.value
      };
  
      this.uploadService.uploadFile(details).then(
        (response) => {
          console.log(response);
          this.router.navigate(['/bundle', response.bundleId]);
        },
        (error) => {
          alert('There was an error in file upload. Please try again.');
          console.error(error);
        }
      )
  }
}


  onFileChange(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.files && target.files.length > 0) {
      const file = target.files[0];
      this.form.get('archive')?.setValue(file);
    }
  }
  
  cancelForm(): void {
    this.form.reset();
  }

  private createForm(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      title: ['', Validators.required],
      comments: [''],
      archive: ['', Validators.required]
    });
  }
}
