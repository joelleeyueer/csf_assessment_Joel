import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { View1formComponent } from './view1form/view1form.component';

const routes: Routes = [
  { path: 'upload', component: View1formComponent },
  { path: '', redirectTo: '/upload', pathMatch: 'full' }

  // Other routes if any
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
