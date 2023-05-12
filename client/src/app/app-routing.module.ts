import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { View1formComponent } from './view1form/view1form.component';
import { View2reviewComponent } from './view2review/view2review.component';

const routes: Routes = [
  { path: '', component: View1formComponent},
  { path: 'upload', component: View1formComponent },
  { path: 'bundle/:bundleId', component: View2reviewComponent},
  { path: '**', redirectTo: '/upload', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
