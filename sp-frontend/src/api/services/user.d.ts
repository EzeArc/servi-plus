export interface Client {
    name: string;
    address: string;
    phone: string;
    mail: string;
    password: string;
    state: boolean;
    image: any; 
  }
  
 export interface AuthResponse {

    token: string;

  }
  

  
  export interface Category {
    name: string;
 }
 
 export interface Provider extends Client {
   salary: number;
   category: Category;
   rating: number;
 }