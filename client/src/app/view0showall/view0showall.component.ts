import { Component, OnInit } from '@angular/core';
import { UploadService } from '../upload.service';
import { PhotoMain } from '../model/model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view0showall',
  templateUrl: './view0showall.component.html',
  styleUrls: ['./view0showall.component.css']
})
export class View0showallComponent {

  bundleList: PhotoMain[] = [];

  constructor(private uploadService: UploadService, private router: Router) { }

  ngOnInit(): void {
    this.uploadService.getAllBundles().subscribe(bundle =>{
      this.bundleList = bundle;
    })
  }

  goToUpload(): void {
    this.router.navigate(['/']);
  }

  

}
