import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class AdderService {
  constructor(private _http: HttpClient) {}

  public current(): Observable<number> {
    return this._http.get<number>('/adder/current')
  }

  public add(num: number): Observable<number> {
    return this._http.post<number>('/adder/', num)
  }

  public acc(num: number): Observable<number> {
    return this._http.post<number>('/adder/acc', num)
  }
}
