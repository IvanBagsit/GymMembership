import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor() { }

  getEmployees(){
    return [
      {"id":1, "name":"Andrew", "age":30},
      {"id":2, "name":"Ivan", "age":28},
      {"id":3, "name":"John", "age":35},
      {"id":4, "name":"Paul", "age":42}
    ];
  }
}
