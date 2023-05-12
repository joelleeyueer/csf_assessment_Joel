import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { View1formComponent } from './view1form/view1form.component';
import { View2reviewComponent } from './view2review/view2review.component';
import { View0showallComponent } from './view0showall/view0showall.component';

const routes: Routes = [
  { path: 'bundles', component: View0showallComponent},
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
