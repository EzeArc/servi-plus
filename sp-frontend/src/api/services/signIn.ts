const ENDPOINT = "http://localhost:8080/api/auth";

export interface LoginParams {
  username: string;
  password: string;
}

interface LoginResponse {
  jwt: string;
}

export default async function login({ username, password }: LoginParams): Promise<string> {
  const res = await fetch(`${ENDPOINT}/login`, {
    method: "POST",
    headers: {
      "Content-type": "application/json",
    },
    body: JSON.stringify({ username, password }),
  });

  if (!res.ok) throw new Error("Response is NOT OK");

  const res_2: LoginResponse = await res.json();
  const { jwt } = res_2;

  return jwt;
}

