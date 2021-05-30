import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { UrlConstans } from '../../constants/url-constant.model';
import { DataMock } from '../../mocks/data-mock';
import { StorageService } from '../storage/storage.service';
import { IDashboardService } from './dashboard.service.type';

@Injectable()
export class DashboardService implements IDashboardService {

  constructor(
    private httpClient: HttpClient,
    private storageService: StorageService
  ) { }

  initData(idCLient: string): Promise<any> {
    if (!environment.production) {
      return Promise.resolve(DataMock.GET_MOCK_DASHBOARD);
    }
    return this.httpClient.get(
      `${UrlConstans.apiUrl}${UrlConstans.summary}${idCLient}`,
      {
        headers: new HttpHeaders({
          'Authorization': this.storageService.getToken()
        }),
        observe: 'response'
      }
    ).pipe(
      map((response) => {
        return response.body;
      }),
      catchError((error) => {
        return throwError(error);
      })
    ).toPromise();
  }
}
