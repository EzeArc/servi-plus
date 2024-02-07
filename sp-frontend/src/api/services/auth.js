export default class AuthService {
  async login(mail, password) {
    const response = await fetch(
      "http://localhost:8080/api/auth/authenticate",
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ mail, password }),
      }
    );
    if (response.ok) {
      const { token } = await response.json();
      localStorage.setItem("jwt", token);
      return true;
    } else {
      throw new Error("Login failed");
    }
  }

  async register(data, headers) {
    const response = await fetch(
      "http://localhost:8080/api/auth/registerClient",
      {
        method: "POST",
        headers: headers,
        body: data,
      }
    );
    return response.ok;
  }

  async registerProvider(data, headers) {
    const formData = new FormData();
    formData.append("file", data.file); // 'file' debe coincidir con el nombre del parámetro en el backend

    const response = await fetch(
      "http://localhost:8080/api/auth/registerProvider",
      {
        method: "POST",
        headers: headers,
        body: formData,
      }
    );

    return response.ok;
  }

  logout() {
    localStorage.removeItem("jwt");
  }

  get token() {
    return localStorage.getItem("jwt");
  }
}
