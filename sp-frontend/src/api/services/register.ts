import type { AuthResponse, Client, Provider } from './user';

async function registerClient(client: Client): Promise<AuthResponse> {
  const response = await fetch('http://localhost:8080/api/auth/registerClient', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(client),
  });

  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }

  return await response.json();
}
  
  
  export async function registerProvider(provider: Provider): Promise<AuthResponse> {
    const response = await fetch('http://localhost:8080/api/auth/registerProvider', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(provider),
    });
  
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
  
    return await response.json();
  }
  