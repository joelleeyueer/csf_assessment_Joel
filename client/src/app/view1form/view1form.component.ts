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
      const formData = new FormData();
      formData.append('name', this.form.get('name')?.value);
      formData.append('title', this.form.get('title')?.value);
      formData.append('comments', this.form.get('comments')?.value);
      formData.append('archive', this.form.get('archive')?.value);
  
      this.uploadService.uploadFile(formData).subscribe(res => {
        console.log(res);
        this.form.reset();
      }, error => {
        console.log('Form submission error:', error);
      });
    } else {
      console.log('Form is not valid');
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
