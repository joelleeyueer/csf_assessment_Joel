import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private http: HttpClient) { }

  uploadFile(data: FormData): Observable<any> {
    return this.http.post('http://localhost:8080/upload', data, {
      reportProgress: true,
      observe: 'events',
      headers: new HttpHeaders({
        'Accept': 'application/json'
      })
    });
  }
  
}
