import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { View1formComponent } from './view1form/view1form.component';
import { UploadService } from './upload.service';
import { HttpClientModule } from '@angular/common/http';
import { View2reviewComponent } from './view2review/view2review.component';

@NgModule({
  declarations: [
    AppComponent,
    View1formComponent,
    View2reviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [UploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
