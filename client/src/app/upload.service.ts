import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable, firstValueFrom } from "rxjs";
import { Details, PhotoMain } from "./model/model";


@Injectable({
  providedIn: 'root'
})
export class UploadService {

  private apiURI = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  uploadFile(details: Details) {
    const outgoingForm = new FormData();
    outgoingForm.append('name', details.name);
    outgoingForm.append('title', details.title);
    outgoingForm.append('comments', details.comments);
    outgoingForm.append('archive', details.archive);

    return firstValueFrom(
      this.http.post<any>(this.apiURI + '/upload', outgoingForm)
    )
      .then(response => {
        console.log('File uploaded successfully');
        return response;
      })
      .catch(err => {
        console.error('Error occurred while uploading file: ' + err);
        throw err; // Re-throw the error so it can be caught by the caller
      });
  }

  async getBundleByBundleId(bundleId: string): Promise<any> {
    const url = `${this.apiURI}/bundle/${bundleId}`;
    return firstValueFrom<any>(this.http.get<any>(url));
  }

  getAllBundles(): Observable<PhotoMain[]> {
    const url = `${this.apiURI}/bundles`;
    return this.http.get<PhotoMain[]>(url);
}

  
}
