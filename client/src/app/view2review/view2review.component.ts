import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Bundle } from '../model/model';
import { Router } from '@angular/router';
import { UploadService } from '../upload.service';


@Component({
  selector: 'app-view2review',
  templateUrl: './view2review.component.html',
  styleUrls: ['./view2review.component.css']
})
export class View2reviewComponent {

  bundleId!: string;
  bundle!: Bundle;
  params$!:Subscription;

  constructor(private route: ActivatedRoute, private uploadService: UploadService) { }

  ngOnInit(): void {
    this.params$ = this.route.params.subscribe(
      (params) => {
        this.bundleId = params['bundleId'];
        this.uploadService.getBundleByBundleId(this.bundleId)
          .then(results => {
            console.info("Bundle results: ", results);
            this.bundle = results;
          })
          .catch(err => {
            console.error("Bundle error: ", err);
          });
      }
    );
  }

  ngOnDestroy(): void {
    this.params$.unsubscribe();
  }

}
