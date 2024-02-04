const ENDPOINT = "http://localhost:8080/api/auth";

export default async function login({ username, password }) {
  const res = await fetch(`${ENDPOINT}/login`, {
    method: "POST",
    headers: {
      "Content-type": "application/json",
    },
    body: JSON.stringify({ username, password }),
  });
  if (!res.ok) throw new Error("Response is NOT OK");
  const res_2 = await res.json();
  const { jwt } = res_2;
  return jwt;
}
