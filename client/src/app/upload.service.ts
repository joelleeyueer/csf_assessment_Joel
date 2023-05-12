import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable, firstValueFrom } from "rxjs";
import { Details } from "./model/model";


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

    firstValueFrom(this.http.post(this.apiURI + '/upload', outgoingForm))
          .then(() => console.log('File uploaded successfully'))
          .catch(err => console.error('Error occurred while uploading file: ' + err));
  }
  
}
